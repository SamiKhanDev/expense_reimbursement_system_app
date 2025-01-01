package com.example.expenseapp.navgraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expenseapp.expensescreen.ExpenseScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "expense_screen") {
        composable("expense_screen") { ExpenseScreen() }
    }
}
