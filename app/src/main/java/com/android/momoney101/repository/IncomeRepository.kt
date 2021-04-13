package com.android.momoney101.repository

import androidx.lifecycle.LiveData
import com.android.momoney101.data.IncomeDao
import com.android.momoney101.model.Income

//Repository for income - abstract access to multiple data source
class IncomeRepository(private val incomeDao: IncomeDao) {

    //A variable of live data list that hold from the income DAO query
    val readAllData: LiveData<List<Income>> = incomeDao.readAllIncomeData()

    //Function that will call the incomeDao's function addincome
    //Pass the income item in the parameter.
    suspend fun addIncome(income: Income){
        incomeDao.addIncome(income)
    }

    //Function that will call the incomeDao's function deleteincome
    //Pass the income item in the parameter.
    suspend fun deleteIncome(income: Income){
        incomeDao.deleteIncome(income)
    }

    //Function that will call the incomeDao's function deleteAllIncomes
    //Delete all income items
    suspend fun deleteAllIncomes(){
        incomeDao.deleteAllIncomes()
    }

    //Function that will call the getTotalIncome function query
    //Return the value of the query
    fun getTotalIncome():Double{
        return incomeDao.getTotalIncome()
    }
}