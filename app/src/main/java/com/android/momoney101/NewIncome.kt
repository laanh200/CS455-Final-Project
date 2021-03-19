package com.android.momoney101

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.momoney101.data.Income
import com.android.momoney101.data.IncomeViewModel
import com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker
import java.util.*


class NewIncome : AppCompatActivity() {

    private lateinit var saveBtn :Button
    private lateinit var incomeAmount: EditText
    private lateinit var incomeDate: DatePicker

    private lateinit var mIncomeViewModel: IncomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_income)

        val actionBar = supportActionBar

        actionBar!!.title = "New Income"

        actionBar.setDisplayHomeAsUpEnabled(true)

        saveBtn = findViewById(R.id.new_income_save_button)

        incomeAmount = findViewById(R.id.new_income_amount)

        incomeDate = findViewById(R.id.new_income_date)

        mIncomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)

        saveBtn.setOnClickListener {

            saveToDatabase()
            finish()
        }
    }
    private fun saveToDatabase(){
        //date picker
        val selectedDate:String =  (incomeDate.dayOfMonth).toString()+ '/' + (incomeDate.month + 1).toString()+ '/' +(incomeDate.year).toString()

        //the income amount
        val incomeValue = convertEditToDouble(incomeAmount)

        if (!(incomeValue.equals(0.0))){
            val income = Income(0,selectedDate,incomeValue)
            mIncomeViewModel.addIncome(income)
            Toast.makeText(this,"Successfully added new income!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun convertEditToDouble(incomeAmount: EditText): Double {

        //convert to string
        var income: String = incomeAmount.getText().toString()
        //create a default variable
        var incomeInDouble = 0.0

        //If the string is not empty
        if(!TextUtils.isEmpty(income)) {
            //convert to double
            incomeInDouble= income.toDouble()
            return incomeInDouble
        }else {
            //tell them to enter the income amount
            Toast.makeText(this, "Enter income amount.",Toast.LENGTH_SHORT).show()
            //set to return 0.0 as the default
            return incomeInDouble
        }
    }

}


