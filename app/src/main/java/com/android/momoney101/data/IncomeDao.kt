package com.android.momoney101.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Data Access Object for income
@Dao
interface IncomeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    //Function that insert the parameter into the database
    suspend fun addIncome(income: Income)

    //Function that will read from the database order by ID
    @Query("SELECT * FROM IncomeListTable ORDER BY id ASC")
    fun readAllIncomeData(): LiveData<List<Income>>
}