package com.example.expenseapp.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expenseapp.data.Category
import com.example.expenseapp.data.CreateExpenseRequest
import com.example.expenseapp.data.Expense
import com.example.expenseapp.data.UpdateExpenseStatusRequest
import com.example.expenseapp.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(private val repository: ExpenseRepository) : ViewModel() {

    private val _categories = mutableStateOf<List<Category>>(emptyList())
    val categories: State<List<Category>> = _categories


    private val _expenses = mutableStateOf<List<Expense>>(emptyList())
    val expenses: State<List<Expense>> = _expenses
    fun fetchCategories() {
        viewModelScope.launch {
            try {
                val response = repository.getCategories()
                if (response.isSuccessful) {
                    _categories.value = response.body() ?: emptyList()
                } else {
                    // Handle API error
                }
            } catch (e: Exception) {
                // Handle exception
            }
        }
    }
    fun fetchPendingExpenses() {
        viewModelScope.launch {
            try {
                val response = repository.getPendingExpenses()
                if (response.isSuccessful) {
                    _expenses.value = response.body() ?: emptyList()
                } else {
                    Log.e("ViewModel", "Error: ${response.errorBody()}")
                }
            } catch (e: Exception) {
                Log.e("ViewModel", "Exception: ${e.message}")
            }
        }
    }


    fun createExpense(employeeId: Long, expenseRequest: CreateExpenseRequest) {
        viewModelScope.launch {
            try {
                val response = repository.createExpense(employeeId, expenseRequest)
                if (response.isSuccessful) {
                    // Handle success
                    Log.d("ExpenseViewModel", "Expense created successfully")
                } else {
                    // Log the error body to get more details
                    Log.e("ExpenseViewModel", "Error creating expense: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // Log any exception that occurs
                Log.e("ExpenseViewModel", "Exception: ${e.message}")
            }
        }

    }

    fun updateExpenseStatus(request: UpdateExpenseStatusRequest) {
        viewModelScope.launch {
            repository.updateExpenseStatus(request)
        }
    }
}
