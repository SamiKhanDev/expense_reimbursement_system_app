package com.example.expenseapp.expensescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.data.Expense
import com.example.expenseapp.viewmodel.ExpenseViewModel

@Composable
fun ExpenseScreen(
    viewModel: ExpenseViewModel = hiltViewModel(),
    onNavigateToCreateExpense: () -> Unit,
    onUpdateExpenseStatus: (Expense) -> Unit
) {
    val expenses = viewModel.expenses.value

    LaunchedEffect(Unit) {
        viewModel.fetchPendingExpenses()
    }

    // Using Scaffold to structure the layout
    Scaffold(
        topBar = {
            // You can add a TopAppBar here if you want, e.g.:
            // TopAppBar(title = { Text("Pending Expenses") })
        },
        content = { paddingValues ->
            // Content of the screen
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Pending Expenses", style = MaterialTheme.typography.labelLarge, modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 16.dp))
                LazyColumn(
                    contentPadding = paddingValues // Ensure content doesn't overlap with FAB
                ) {
                    items(expenses) { expense ->
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                .clickable { onUpdateExpenseStatus(expense) }
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text("Description: ${expense.description}")
                                Text("Amount: ${expense.amount}")
                                Text("Submitted: ${expense.submitDate}")
                            }
                        }
                    }
                }
            }
        },
        floatingActionButton = {
            // Floating Action Button positioned at the bottom right
            FloatingActionButton(
                onClick = { onNavigateToCreateExpense() },
                modifier = Modifier.padding(16.dp)
            ) {
                Text("+") // Customize as needed
            }
        },
        floatingActionButtonPosition = FabPosition.End, // Position it at the bottom-right
        modifier = Modifier.fillMaxSize()
    )
}
