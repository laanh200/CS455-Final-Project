package com.android.momoney101.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity class for expense list table
@Entity(tableName = "ExpenseListTable")
data class Expense (
        //These are the fields of the tables
        @PrimaryKey(autoGenerate = true) val id:Int,
        val expenseDate:String,
        val expenseName: String,
        val expenseType: String,
        val expenseAmount: Double,
        )