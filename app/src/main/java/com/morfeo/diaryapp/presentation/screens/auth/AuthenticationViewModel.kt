package com.morfeo.diaryapp.presentation.screens.auth

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.morfeo.diaryapp.util.Constant.APP_ID_MONGO
import io.realm.kotlin.mongodb.App
import io.realm.kotlin.mongodb.Credentials
import io.realm.kotlin.mongodb.GoogleAuthType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthenticationViewModel : ViewModel() {

    var authenticated = mutableStateOf(false)
        private set

    var loadingState = mutableStateOf(false)
        private set

    fun setLoading(loading: Boolean) {
        loadingState.value = loading
    }

    fun signInWithMongoAtlas(
        tokenId: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ){
        setLoading(true)
        viewModelScope.launch {
            try {
                val result = withContext(Dispatchers.IO){
                    App.Companion.create(APP_ID_MONGO).login(
                        Credentials.jwt(tokenId)
                    ).loggedIn
                }
                withContext(Dispatchers.Main){
                    if(result){
                        onSuccess()
                        delay(600)
                        authenticated.value = true
                    }
                }
            }catch (e:Exception){
                withContext(Dispatchers.Main){
                    onError(e)
                }
            }
        }.invokeOnCompletion { setLoading(false) }
    }
}