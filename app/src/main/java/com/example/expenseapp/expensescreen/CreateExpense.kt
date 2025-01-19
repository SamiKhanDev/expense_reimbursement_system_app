package com.example.expenseapp.expensescreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.expenseapp.data.Category
import com.example.expenseapp.data.CreateExpenseRequest
import com.example.expenseapp.viewmodel.ExpenseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateExpense(
    viewModel: ExpenseViewModel = hiltViewModel(),
    onBack: () -> Unit
) {
    var description by remember { mutableStateOf("") }
    var amount by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf<Category?>(null) }
    var isDropdownExpanded by remember { mutableStateOf(false) }
    var employeeId by remember { mutableStateOf("") }  // Variable to store employeeId

    val categories = viewModel.categories.value

    LaunchedEffect(Unit) {
        viewModel.fetchCategories()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Expense") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .padding(16.dp)
            ) {
                // Employee ID TextField
                TextField(
                    value = employeeId,
                    onValueChange = { employeeId = it },
                    label = { Text("Employee ID") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF0F0F0)
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Description TextField
                TextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Description") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF0F0F0)
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Amount TextField
                TextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color(0xFFF0F0F0)
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
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
                            .background(Color.White, RoundedCornerShape(8.dp))
                            .padding(horizontal = 16.dp, vertical = 12.dp)
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

                // Submit Button
                Button(
                    onClick = {
                        if (selectedCategory == null || employeeId.isBlank() || amount.isBlank() || description.isBlank()) {
                            Log.e("CreateExpense", "All fields must be filled")
                            return@Button
                        }

                        val expenseRequest = CreateExpenseRequest(
                            employeeId = employeeId.toLong(),
                            amount = amount.toDoubleOrNull() ?: 0.0,
                            description = description,
                            //Modified CreateExpense composable function when creating the request
                            categoryId = selectedCategory!!.id
                        )

                        viewModel.createExpense(expenseRequest)
                        onBack()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text("Submit")
                }

            }
        }
    )
}
