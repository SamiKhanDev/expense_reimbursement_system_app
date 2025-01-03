package com.example.expenseapp.expensescreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.expenseapp.data.Expense

@Composable
fun UpdateExpenseStatusDialog(
    expense: Expense,
    onDismiss: () -> Unit,
    onUpdate: (Long) -> Unit
) {
    var statusId by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text("Update Status") },
        text = {
            Column {
                Text("Expense: ${expense.description}")
                TextField(
                    value = statusId,
                    onValueChange = { statusId = it },
                    label = { Text("Status ID") },
//                    keyboardType = KeyboardType.Number
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onUpdate(statusId.toLongOrNull() ?: 0L)
                onDismiss()
            }) {
                Text("Update")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}
