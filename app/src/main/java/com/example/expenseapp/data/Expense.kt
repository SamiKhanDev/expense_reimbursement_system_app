package com.example.expenseapp.data

data class Expense(
    val id: Long? = null,
    val employeeId: Long,
    val amount: Double,
    val description: String,
    val submitDate: String? = null
)

data class UpdateExpenseStatusRequest(
    val expenseId: Long,
    val statusId: Long
)
