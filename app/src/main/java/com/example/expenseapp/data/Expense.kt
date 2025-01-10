package com.example.expenseapp.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Expense(
    val id: Long? = null,
    val employeeId: Long,
    val categoryId:Int,
    val amount: Double,
    val description: String,
    val submitDate: String? = null
): Parcelable

 data class Category(
    val id: Int,
     val name: String
)

data class CategoryPackage(
    val id: Int,
    val name: String
)

data class ExpenseCategory(
    val id: Int, val name: String)

data class UpdateExpenseStatusRequest(
    val expenseId: Long,
    val statusId: Long
)
//To align with the backend's expectations,
// modify your CreateExpenseRequest class to include categoryId directly
data class CreateExpenseRequest(
    val employeeId: Long,
    val amount: Double,
    val description: String,
    val categoryId: Int
)


data class CategoryRequest(
    val id: Int
)
