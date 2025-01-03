package com.example.expenseapp.navgraph

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.expenseapp.data.Expense
import com.example.expenseapp.data.UpdateExpenseStatusRequest
import com.example.expenseapp.expensescreen.CreateExpense
import com.example.expenseapp.expensescreen.ExpenseScreen
import com.example.expenseapp.expensescreen.UpdateExpenseStatusDialog
import com.example.expenseapp.viewmodel.ExpenseViewModel

@Composable
fun ExpenseAppNav(viewModel: ExpenseViewModel = hiltViewModel()) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "expense_screen") {
        composable("expense_screen") {
            ExpenseScreen(
                viewModel = viewModel,
                onNavigateToCreateExpense = { navController.navigate("create_expense_screen") },
                onUpdateExpenseStatus = { expense ->
                    navController.currentBackStackEntry?.savedStateHandle?.set("expense", expense)
                    navController.navigate("update_expense_status_screen")
                }
            )
        }
        composable("create_expense_screen") {
           CreateExpense (
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
        composable("update_expense_status_screen") {
            val expense =
                navController.previousBackStackEntry?.savedStateHandle?.get<Expense>("expense")
            expense?.let {
                UpdateExpenseStatusDialog(
                    expense = it,
                    onDismiss = { navController.popBackStack() },
                    onUpdate = { statusId ->
                        viewModel.updateExpenseStatus(
                            UpdateExpenseStatusRequest(expenseId = it.id!!, statusId = statusId)
                        )
                    }
                )
            }
        }
    }
}
