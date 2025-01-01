package com.example.expenseapp.expensescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.viewmodel.ExpenseViewModel

@Composable
fun ExpenseScreen(viewModel: ExpenseViewModel = hiltViewModel()) {
    val expenses = viewModel.expenses.value
    LaunchedEffect(Unit) {
        viewModel.fetchPendingExpenses()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        Text("Pending Expenses", style = MaterialTheme.typography.bodyLarge)
        LazyColumn {
            items(expenses) { expense ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
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
}
