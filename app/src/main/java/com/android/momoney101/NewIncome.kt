package com.android.momoney101

import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.android.momoney101.model.Income
import com.android.momoney101.viewmodel.IncomeViewModel


class NewIncome : AppCompatActivity() {

    //Create late init variables for linking with xml file
    private lateinit var saveBtn :Button
    private lateinit var incomeAmount: EditText
    private lateinit var incomeDate: DatePicker

    private lateinit var mIncomeViewModel: IncomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_income)

        val actionBar = supportActionBar

        //Set the name of activity title
        actionBar!!.title = "New Income"

        //Back to main activity
        actionBar.setDisplayHomeAsUpEnabled(true)

        //link the late init variable with the item in activity_income_list.xml
        saveBtn = findViewById(R.id.new_income_save_button)
        incomeAmount = findViewById(R.id.new_income_amount)
        incomeDate = findViewById(R.id.new_income_date)

        //This will create ViewModels and retain them in a store of the incomeViewModel
        mIncomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)

        //The save button will call the savetodatabase function
        saveBtn.setOnClickListener {
            saveToDatabase()
            //Return to main activity
            finish()
        }
    }

    /*Function that being call to add the new income item into the database
       An income item have 3 columns: id, date, and amount
       date is string
       id is integer
       amount is in double
    */
    private fun saveToDatabase(){
        //date picker
        val selectedDate:String =  (incomeDate.dayOfMonth).toString()+ '/' + (incomeDate.month + 1).toString()+ '/' +(incomeDate.year).toString()

        //the income amount
        val incomeValue = convertEditToDouble()

        //Check if amount is not equal to zero
        if (!(incomeValue.equals(0.0))){
            //If not equal to zero then create an income item with the value of 3 colums
            val income = Income(0,selectedDate,incomeValue)
            //Add to database
            mIncomeViewModel.addIncome(income)
            //Toast for successful add
            Toast.makeText(this,"Successfully added new income!", Toast.LENGTH_SHORT).show()
        }else{
            //toast for unsuccessful failure
            Toast.makeText(this,"Failure in adding new income. Item need income amount!", Toast.LENGTH_SHORT).show()
        }
    }

    /*
        This function is to be called by the saveToDatabase function
        The purpose of function is to convert the edit text into string
        Check if the string is empty
        convert the string into double and return the double
     */
    private fun convertEditToDouble(): Double {

        //convert to string
        var income: String = incomeAmount.text.toString()
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


