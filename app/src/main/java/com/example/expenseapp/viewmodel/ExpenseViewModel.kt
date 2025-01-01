package com.example.expenseapp.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expenseapp.data.Expense
import com.example.expenseapp.data.UpdateExpenseStatusRequest
import com.example.expenseapp.repository.ExpenseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExpenseViewModel @Inject constructor(private val repository: ExpenseRepository) : ViewModel() {

    private val _expenses = mutableStateOf<List<Expense>>(emptyList())
    val expenses: State<List<Expense>> = _expenses

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


    fun createExpense(employeeId: Long, expense: Expense) {
        viewModelScope.launch {
            repository.createExpense(employeeId, expense)
        }
    }

    fun updateExpenseStatus(request: UpdateExpenseStatusRequest) {
        viewModelScope.launch {
            repository.updateExpenseStatus(request)
        }
    }
}
