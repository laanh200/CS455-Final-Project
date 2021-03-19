package com.android.momoney101.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
Expense view model
Provide data to the UI
Help the repository and the UI communicate
 */
class ExpenseViewModel(application: Application): AndroidViewModel(application) {
    //Create a list of live data expense
    val readAllExpenseData: LiveData<List<Expense>>
    //An expense repository variable
    private val repository: ExpenseRepository

    //Executed when expense view model is called
    init {
        //Create an expense dao by getting the expense database of the application
        val expenseDao = ExpenseDatabase.getDatabase(application).ExpenseDao()
        //set the repository equal to the expense DAO repository
        repository = ExpenseRepository(expenseDao)
        //Assign the repository read all data variable to the list of live data expense
        readAllExpenseData = repository.readAllData
    }

    //This function is use add new expense to the repository
    fun addExpense(expense: Expense){
        //Run this code in the back ground thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExpense(expense)
        }
    }
}


