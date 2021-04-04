package com.android.momoney101.data

import androidx.lifecycle.LiveData
import androidx.room.*

import com.android.momoney101.model.Income

//Data Access Object for income
@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //Function that insert the parameter into the database
    suspend fun addIncome(income: Income)

    //Function that will read from the database order by ID
    @Query("SELECT * FROM IncomeListTable ORDER BY id ASC")
    fun readAllIncomeData(): LiveData<List<Income>>

    //Function that will delete parameter income item from the database
    @Delete
    suspend fun deleteIncome(income: Income)

    //Function that will query the total amount of income
    @Query("SELECT SUM(incomeAmount) FROM IncomeListTable")
    fun getTotalIncome(): Double
}