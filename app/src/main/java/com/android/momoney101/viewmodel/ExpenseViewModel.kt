package com.android.momoney101.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.momoney101.data.ExpenseDatabase
import com.android.momoney101.model.Expense
import com.android.momoney101.repository.ExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/*
Expense view model
Provide data to the UI
Help the repository and the UI communicate
 */
class ExpenseViewModel (application: Application): AndroidViewModel(application) {

    //Create a list of live data expense
    val readAllExpenseData: LiveData<List<Expense>>

    //An expense repository variable
    private val repository: ExpenseRepository

    //Executed when expense view model is called
    init {
        //Create an expense dao variable by getting the expense database of the application
        val expenseDao = ExpenseDatabase.getDatabase(application).ExpenseDao()
        //set the repository equal to the expense DAO repository
        repository = ExpenseRepository(expenseDao)
        //Assign the repository read all data variable to the list of live data expense
        readAllExpenseData = repository.readAllData

    }

    //This function is use add new expense by calling the addExpense function in expense repository
    fun addExpense(expense: Expense){
        //Run this code in the back ground thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addExpense(expense)
        }
    }

    //This function is use to delete expense by calling the deleteExpense function in expense repository
    fun deleteExpense(expense: Expense){
        //Run this code in the back ground thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteExpense(expense)}
    }

    //This function is use to delete all the current expenses by calling the deleteAllExpenses function in expense repository
    fun deleteAllExpenses(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllExpenses()
        }
    }

    //This function is use to call the getTotalExpense function in the expense repository
    // and return the value to the activity
    fun getTotalExpense():Double{
        return repository.getTotalExpense()
    }

}







