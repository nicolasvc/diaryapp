package com.morfeo.diaryapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.morfeo.diaryapp.navigation.Screen
import com.morfeo.diaryapp.navigation.SetUpNavGraph
import com.morfeo.diaryapp.ui.theme.DiaryappTheme
import com.morfeo.diaryapp.util.Constant.APP_ID_MONGO
import io.realm.kotlin.mongodb.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            DiaryappTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    SetUpNavGraph(
                        startDestination = getStartDestination(),
                        navController = navController
                    )

                }
            }
        }
    }
}

private fun getStartDestination(): String {
    val user = App.Companion.create(APP_ID_MONGO).currentUser
    return if (user != null && user.loggedIn) return Screen.Home.route else Screen.Authentication.route
}


