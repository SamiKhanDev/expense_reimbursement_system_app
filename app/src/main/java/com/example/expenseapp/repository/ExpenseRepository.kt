package com.example.expenseapp.repository

import com.example.expenseapp.apis.ExpenseApi
import com.example.expenseapp.data.CreateExpenseRequest
import com.example.expenseapp.data.Expense
import com.example.expenseapp.data.UpdateExpenseStatusRequest
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseRepository @Inject constructor(private val api: ExpenseApi) {

    suspend fun getPendingExpenses() = api.getPendingExpenses()

    suspend fun getCategories() = api.getCategories()

    suspend fun createExpense(expenseRequest: CreateExpenseRequest): Response<Expense> {
        return api.createExpense(expenseRequest) // The employeeId will now be part of the request body
    }

    suspend fun updateExpenseStatus(request: UpdateExpenseStatusRequest) = api.updateExpenseStatus(request)
}
