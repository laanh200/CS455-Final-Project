package com.android.momoney101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText

class NewIncome : AppCompatActivity() {

    private lateinit var saveBtn :Button
    private lateinit var incomeAmount: EditText
    private lateinit var incomeDate: DatePicker


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_income)

        val actionBar = supportActionBar

        actionBar!!.title = "New Income"

        actionBar.setDisplayHomeAsUpEnabled(true)

        saveBtn = findViewById(R.id.new_income_save_button)

        incomeAmount = findViewById(R.id.new_income_amount)




        incomeDate = findViewById(R.id.new_income_date)

        saveBtn.setOnClickListener {

            // checkTextVariables()
        }
    }
/*

    private fun convertEditToDouble(incomeAmount: EditText): Double {

        var income: String = incomeAmount.getText().toString()
        var incomeInDouble = 0.0

        if(!TextUtils.isEmpty(income)) {

            var incomeInDouble: Double= income.toDouble()
            return incomeInDouble
        }else {
            return incomeInDouble
        }
    }

    private fun checkTextVariables(){
        convertEditToDouble(incomeAmount)
    }*/
}