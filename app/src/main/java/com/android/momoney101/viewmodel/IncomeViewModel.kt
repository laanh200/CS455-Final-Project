package com.android.momoney101.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.android.momoney101.data.IncomeDatabase
import com.android.momoney101.model.Income
import com.android.momoney101.repository.IncomeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/*
income view model
Provide data to the UI
Help the repository and the UI communicate
 */
class IncomeViewModel(application: Application):AndroidViewModel(application) {

    //Create a list of live data expense
    val readAllIncomeData: LiveData<List<Income>>

    //An expense repository variable
    private val repository: IncomeRepository

    //Executed when income view model is called
    init {
        //Create an income dao by getting the income database of the application
        val incomeDao = IncomeDatabase.getDatabase(application).IncomeDao()
        //set the repository equal to the income DAO repository
        repository = IncomeRepository(incomeDao)
        //Assign the repository read all data variable to the list of live data income
        readAllIncomeData = repository.readAllData
    }

    //This function is use to add new income by calling the addincome function in income repository
    fun addIncome(income: Income){
        //Run this code in the background thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIncome(income)
        }
    }

    //This function is use to delete expense item by calling the deleteincome function in income repository
    fun deleteIncome(income: Income){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteIncome(income)
        }
    }
    //This function is use to call the getTotalIncome function in the income repository
    // and return the value to the activity
    fun getTotalIncome():Double{
        return repository.getTotalIncome()
    }
}