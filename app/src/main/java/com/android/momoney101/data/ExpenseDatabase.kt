package com.android.momoney101.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Database for expense
@Database(entities = [Expense::class], version =1, exportSchema = false)
abstract class ExpenseDatabase:RoomDatabase() {

    abstract fun ExpenseDao(): ExpenseDao
    companion object{
        @Volatile
        //Create an empty instance of expense database
        private var INSTANCE: ExpenseDatabase? = null

        //Function to get the database
        fun getDatabase(context: Context):ExpenseDatabase{
            val temporaryInstance = INSTANCE
            //Check if the temporary instance is already exist
            if(temporaryInstance != null){
                //If yes, then return that instance
                return temporaryInstance
            }
            //Creating a new instance if not exist
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ExpenseDatabase::class.java,
                    "ExpenseList_Database"
                ).build()
                //Set the instance equal to the brand new created instance
                INSTANCE = instance
                //return the new instance
                return instance
            }
        }
    }
}