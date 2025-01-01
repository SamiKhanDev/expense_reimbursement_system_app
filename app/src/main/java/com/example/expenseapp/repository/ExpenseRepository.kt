package com.example.expenseapp.repository

import com.example.expenseapp.apis.ExpenseApi
import com.example.expenseapp.data.Expense
import com.example.expenseapp.data.UpdateExpenseStatusRequest
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ExpenseRepository @Inject constructor(private val api: ExpenseApi) {

    suspend fun getPendingExpenses() = api.getPendingExpenses()

    suspend fun createExpense(employeeId: Long, expense: Expense) = api.createExpense(employeeId, expense)

    suspend fun updateExpenseStatus(request: UpdateExpenseStatusRequest) = api.updateExpenseStatus(request)
}
