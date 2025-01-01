package com.example.expenseapp.apis

import com.example.expenseapp.data.Expense
import com.example.expenseapp.data.UpdateExpenseStatusRequest
import retrofit2.Response
import retrofit2.http.*

interface ExpenseApi {
    @GET("/api/expenses/pending")
    suspend fun getPendingExpenses(): Response<List<Expense>>

    @POST("/api/expenses/employee/{employeeId}/expense")
    suspend fun createExpense(
        @Path("employeeId") employeeId: Long,
        @Body expense: Expense
    ): Response<Expense>

    @PATCH("/api/expenses/status")
    suspend fun updateExpenseStatus(@Body request: UpdateExpenseStatusRequest): Response<String>
}
