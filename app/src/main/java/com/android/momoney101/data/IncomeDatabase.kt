package com.android.momoney101.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Income database
@Database(entities = [Income::class], version = 1, exportSchema = false)
abstract class IncomeDatabase :RoomDatabase(){

    abstract fun IncomeDao():IncomeDao
    companion object{
        @Volatile
        //Create an empty instance of income database
        private var INSTANCE: IncomeDatabase? = null

        //Get the database
        fun getDatabase(context: Context):IncomeDatabase{
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
                    IncomeDatabase::class.java,
                    "IncomeList_Database"
                ).build()
                //Set the empty instance equal to the newly created instance
                INSTANCE = instance
                //return the new instance
                return instance
            }
        }
    }
}