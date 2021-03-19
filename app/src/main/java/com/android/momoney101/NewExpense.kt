package com.android.momoney101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.android.momoney101.data.Expense
import com.android.momoney101.data.ExpenseViewModel

class NewExpense : AppCompatActivity() {

    lateinit var expenseDate: DatePicker
    lateinit var expenseAmount: EditText
    lateinit var saveBtn: Button
    var expenseType: String =""
    lateinit var expenseName:EditText
    lateinit var spinner: Spinner
    private lateinit var mExpenseViewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_expense)

        val actionBar = supportActionBar

        actionBar!!.title = "New Expense"

        actionBar.setDisplayHomeAsUpEnabled(true)

        //Link the variables with the items in xml
        saveBtn = findViewById(R.id.new_expense_save_button)
        expenseDate = findViewById(R.id.new_expense_date)
        expenseAmount = findViewById(R.id.new_expense_amount)
        expenseName = findViewById(R.id.new_expense_title)

        spinner = findViewById(R.id.new_expense_type_spinner)

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.new_expense_type_spinner_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter

        }
        expenseType = spinner.selectedItem.toString()
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        saveBtn.setOnClickListener {

            saveToDatabase()
            finish()
        }


    }
    private fun saveToDatabase(){

        //the name of the expense
        val expenseTitle:String = checkTextVariables()

        //date picker
        val selectedDate:String =  (expenseDate.dayOfMonth).toString()+ '/' + (expenseDate.month + 1).toString()+ '/' +(expenseDate.year).toString()


        //The amount
        val expenseDollar = convertEditToDouble(expenseAmount)

        if (expenseTitle.isNotBlank() || expenseDollar.equals(0.0)){
            //create expense object
            val expense = Expense(0, selectedDate,expenseTitle, expenseType, expenseDollar)
            //Add expense item to database
            mExpenseViewModel.addExpense(expense)
            Toast.makeText(this,"Successfully added new expense!", Toast.LENGTH_SHORT).show()
        }

    }


    private fun convertEditToDouble(incomeAmount: EditText): Double {
        //Convert to string
        var expense: String = expenseAmount.getText().toString()

        //Create a variable
        var expenseInDouble = 0.0

        //If the expense variable is not empty
        if(!TextUtils.isEmpty(expense)) {
            //if not empty then convert to double
            expenseInDouble= expense.toDouble()
            return expenseInDouble

        }else {
            //If empty then tell them to enter amount
            Toast.makeText(this, "Enter expense amount.",Toast.LENGTH_SHORT).show()
            return expenseInDouble
        }
    }

    private fun checkTextVariables():String{
        //Convert to string
        var name: String = expenseName.getText().toString()

        //Check if variable is empty
        if(name.isNullOrBlank()){
            //if yes, then tell them to enter name
            Toast.makeText(this, "Enter expense name.",Toast.LENGTH_SHORT).show()
        }
        return name
    }
}