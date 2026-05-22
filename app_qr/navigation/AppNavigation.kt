package com.example.app_qr.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// IMPORTS UNIFICADOS CON LA RUTA REAL (CON X)
import com.example.app_qr.screens.HomeScreen
import com.example.app_qr.screens.PlanScreen
import com.example.app_qr.screens.CampoLaboralScreen
import com.example.app_qr.screens.EspecialidadesScreen
import com.example.app_qr.screens.QuizScreen
import com.example.app_qr.screens.QRScreen
import com.example.app_qr.screens.SplashScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash") {
        composable(route = "splash") { SplashScreen(navController) }
        composable(route = "home") { HomeScreen(navController) }
        composable(route = "plan") { PlanScreen(navController) }
        composable(route = "campo") { CampoLaboralScreen(navController) }
        composable(route = "especialidades") { EspecialidadesScreen(navController) }
        composable(route = "quiz") { QuizScreen(navController) }
        composable(route = "qr") { QRScreen(navController) }
    }
}