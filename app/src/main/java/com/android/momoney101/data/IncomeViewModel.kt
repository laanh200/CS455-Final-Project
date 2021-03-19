package com.android.momoney101.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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


    //This function is use add new income to the repository
    fun addIncome(income: Income){
        //Run this code in the background thread
        viewModelScope.launch(Dispatchers.IO) {
            repository.addIncome(income)
        }
    }
}