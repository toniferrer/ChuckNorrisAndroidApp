package com.toniferrer.chucknorrisapp2.navegacion

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.toniferrer.chucknorrisapp2.paginas.CategoriaScreen
import com.toniferrer.chucknorrisapp2.paginas.PrincipalScreen

@Composable
fun NavegacionApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "PrincipalScreen"
    ) {
        composable("PrincipalScreen") {
            PrincipalScreen(navController)
        }
        composable(
            route = "CategoriaScreen/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStackEntry ->
            val categoria = backStackEntry.arguments?.getString("category") ?: ""
            CategoriaScreen(categoria, navController)
        }
    }
}