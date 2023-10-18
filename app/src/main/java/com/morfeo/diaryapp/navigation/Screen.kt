package com.morfeo.diaryapp.navigation

import com.morfeo.diaryapp.util.Constant.WRITE_SCREEN_ARGUMENT_KEY

sealed class Screen(val route:String) {
    object Authentication : Screen(route = "Authentication_screen")
    object Home : Screen(route = "home_screen")
    object Write  : Screen(route = "write_screen?$WRITE_SCREEN_ARGUMENT_KEY={$WRITE_SCREEN_ARGUMENT_KEY}"){
        fun passDiaryId(diaryId:String?) = "write_screen?$WRITE_SCREEN_ARGUMENT_KEY=$diaryId"

    }

}

