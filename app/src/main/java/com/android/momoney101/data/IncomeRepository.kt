package com.android.momoney101.data

import androidx.lifecycle.LiveData

//Repository for income - abstract access to multiple data source
class IncomeRepository(private val incomeDao:IncomeDao) {

    //A variable of live data list that hold from the income DAO query
    val readAllData: LiveData<List<Income>> = incomeDao.readAllIncomeData()

    suspend fun addIncome(income: Income){
        incomeDao.addIncome(income)
    }
}