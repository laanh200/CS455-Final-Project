package com.android.momoney101.repository

import androidx.lifecycle.LiveData
import com.android.momoney101.data.ExpenseDao
import com.android.momoney101.model.Expense

//Repository for expense - abstract access to multiple data source
class ExpenseRepository(private val expenseDao: ExpenseDao) {

    //A variable of live data list that hold from the expense DAO query
    val readAllData:LiveData<List<Expense>> = expenseDao.readAllExpenseData()

    //Function that will call the expenseDao's function addexpense
    //Pass the expense item in the parameter.
    suspend fun addExpense(expense: Expense){
        expenseDao.addExpense(expense)
    }

    //Function that will call the expenseDao's function deleteExpense
    //Pass the expense item in the parameter.
    suspend fun deleteExpense(expense: Expense){
        expenseDao.deleteExpense(expense)
    }
}