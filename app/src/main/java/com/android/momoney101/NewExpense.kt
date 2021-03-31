package com.android.momoney101


import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import com.android.momoney101.model.Expense
import com.android.momoney101.viewmodel.ExpenseViewModel

class NewExpense : AppCompatActivity() {

    //Create variables for late init
    lateinit var expenseDate: DatePicker
    lateinit var expenseAmount: EditText
    lateinit var saveBtn: Button
    lateinit var expenseName: EditText
    lateinit var spinner: Spinner
    private lateinit var mExpenseViewModel: ExpenseViewModel

    //Creatg a empty string variable to set the expense type later.
    var expenseType: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_expense)

        val actionBar = supportActionBar

        //Set the name of activity title
        actionBar!!.title = "New Expense"

        //Back to main activity
        actionBar.setDisplayHomeAsUpEnabled(true)

        //Link the variables with the items in activity_new_expense xml
        saveBtn = findViewById(R.id.new_expense_save_button)
        expenseDate = findViewById(R.id.new_expense_date)
        expenseAmount = findViewById(R.id.new_expense_amount)
        expenseName = findViewById(R.id.new_expense_title)
        spinner = findViewById(R.id.new_expense_type_spinner)

        //Create a variable and link it with the new_expense_type_spinner_array in string.xml
        val types = resources.getStringArray(R.array.new_expense_type_spinner_array)

        //If spinner is not empty
        if (spinner != null) {
            //create an adapter for
            val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item ,types)

            //Set array adapter to spinner adapter
            spinner.adapter = adapter

            //Set on item selected listener
            spinner.onItemSelectedListener = object :
                    AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    //Set the expensetype variable to the currently selected item in the spinner drop down
                    expenseType = parent.getItemAtPosition(position).toString()
                }
                //do nothing
                override fun onNothingSelected(parent: AdapterView<*>) {
                    // nothing
                }
            }
        }
        //Set the expensetype variable to the selected item
        expenseType = spinner.selectedItem.toString()

        //This will create ViewModels and retain them in a store of the ExpenseViewModel
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        //The save button will call the savetodatabase function
        saveBtn.setOnClickListener {
            //call the function to add item to database
            saveToDatabase()
            //Return to main activity
            finish()
        }
    }

    /*Function that being call to add the new expense item into the database
        An expense item have 5 columns: id, date, title, type and amount
        date, title, and type are string
        id is integer
        amount is in double
     */
    private fun saveToDatabase(){
        //the name of the expense
        val expenseTitle:String = checkTextVariables()

        //date picker
        val selectedDate:String =  (expenseDate.dayOfMonth).toString()+ '/' + (expenseDate.month + 1).toString()+ '/' +(expenseDate.year).toString()

        //The amount
        val expenseDollar = convertEditToDouble(expenseAmount)

        //If Check if the title name is empty or the amount is empty or not
        if (expenseTitle.isNotBlank() || expenseDollar.equals(0.0)){
            //create expense object
            val expense = Expense(0, selectedDate,expenseTitle, expenseType, expenseDollar)
            //Add expense item to database
            mExpenseViewModel.addExpense(expense)
            //Toast to let user know that item is added
            Toast.makeText(this,"Successfully added new expense!", Toast.LENGTH_SHORT).show()
        }else{
            //Toast to let user know that item is not added
            Toast.makeText(this,"Failure in adding new expense. Item need a name or a cost!", Toast.LENGTH_SHORT).show()
        }

    }

    /*
        This function is to be called by the saveToDatabase function
        The purpose of function is to convert the edit text into string
        Check if the string is empty
        convert the string into double and return the double
     */
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

    /*
        This function is to be called by the saveToDatabase function
        The purpose of function is to convert the edit text into string
        Check if the name string is empty
        Return the string if the variable is not empty
     */
    private fun checkTextVariables():String{
        //Convert to string
        var name: String = expenseName.getText().toString()

        //Check if variable is empty
        if(name.isNullOrBlank()){
            //if yes, then tell them to enter name
            Toast.makeText(this, "Enter expense name.",Toast.LENGTH_SHORT).show()
        }
        //return expense name
        return name
    }



}