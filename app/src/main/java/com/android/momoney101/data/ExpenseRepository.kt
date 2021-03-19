package com.android.momoney101.data

import androidx.lifecycle.LiveData

//Repository for expense - abstract access to multiple data source
class ExpenseRepository(private val expenseDao:ExpenseDao) {

    //A variable of live data list that hold from the expense DAO query

    val readAllData:LiveData<List<Expense>> = expenseDao.readAllExpenseData()

    suspend fun addExpense(expense: Expense){
        expenseDao.addExpense(expense)
    }
}