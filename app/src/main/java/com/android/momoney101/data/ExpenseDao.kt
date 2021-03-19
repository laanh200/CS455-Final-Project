package com.android.momoney101.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Data Access Object for expense
@Dao
interface ExpenseDao {

    //Function that insert the parameter into the database
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addExpense(expense: Expense)

    //Function that will read from the database order by ID
    @Query("SELECT * FROM ExpenseListTable ORDER BY id ASC")
    fun readAllExpenseData():LiveData<List<Expense>>

}