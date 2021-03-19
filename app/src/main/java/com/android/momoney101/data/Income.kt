package com.android.momoney101.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity class for income list table
@Entity(tableName = "IncomeListTable")
data class Income
(       //These are the fields of the tables
        @PrimaryKey(autoGenerate = true) val id:Int,
        val incomeDate: String,
        val incomeAmount: Double
        )