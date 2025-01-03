package com.example.expenseapp.expensescreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.data.Category
import com.example.expenseapp.data.CategoryRequest
import com.example.expenseapp.data.CreateExpenseRequest
import com.example.expenseapp.viewmodel.ExpenseViewModel
@Composable
fun CreateExpense(
    viewModel: ExpenseViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var isDropdownExpanded by remember { mutableStateOf(false) }

    val categories = viewModel.categories.value

    LaunchedEffect(Unit) {
        viewModel.fetchCategories()
    }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = amount,
            onValueChange = { amount = it },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Dropdown for Category Selection
        Column {
            Text(
                text = selectedCategory?.name ?: "Select Category",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .clickable { isDropdownExpanded = true }
            )
            DropdownMenu(
                expanded = isDropdownExpanded,
                onDismissRequest = { isDropdownExpanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category.name) },
                        onClick = {
                            selectedCategory = category
                            isDropdownExpanded = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (selectedCategory == null) {
                    // Show an error or toast message if the category isn't selected
                    return@Button
                }

                val expenseRequest = CreateExpenseRequest(
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    description = description,
                    category = CategoryRequest(id = selectedCategory!!.id)
                )

                viewModel.createExpense(1L, expenseRequest)

                onBack()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }
    }
}
