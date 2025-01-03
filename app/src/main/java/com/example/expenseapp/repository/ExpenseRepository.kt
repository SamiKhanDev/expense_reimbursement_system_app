package com.example.expenseapp.repository

import com.example.expenseapp.apis.ExpenseApi
import com.example.expenseapp.data.CreateExpenseRequest
import com.example.expenseapp.data.UpdateExpenseStatusRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseRepository @Inject constructor(private val api: ExpenseApi) {

    suspend fun getPendingExpenses() = api.getPendingExpenses()

    suspend fun getCategories() = api.getCategories()

    suspend fun createExpense(employeeId: Long, expense: CreateExpenseRequest) = api.createExpense(employeeId, expense)

    suspend fun updateExpenseStatus(request: UpdateExpenseStatusRequest) = api.updateExpenseStatus(request)
}
