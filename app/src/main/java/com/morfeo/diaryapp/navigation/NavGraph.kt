package com.morfeo.diaryapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun SetUpNavGraph(startDestination:String,navController: NavHostController) {
   NavHost(navController = navController, startDestination = startDestination){
       authenticationRoute()
       homeRoute()
       writeRoute()
   }
}

fun NavGraphBuilder.authenticationRoute(){
    composable(route=Screen.Authentication.route){

    }
}

fun NavGraphBuilder.homeRoute(){
    composable(route=Screen.Home.route){

    }
}
fun NavGraphBuilder.writeRoute(){
    composable(route=Screen.Write.route){

    }
}