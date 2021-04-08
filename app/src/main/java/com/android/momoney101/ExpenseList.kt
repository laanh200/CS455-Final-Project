package com.android.momoney101


import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.momoney101.list.ExpenseListAdapter
import com.android.momoney101.model.Expense
import com.android.momoney101.viewmodel.ExpenseViewModel


class ExpenseList : AppCompatActivity() {

    //Create late init variable of type Expense View Model
    private lateinit var mExpenseViewModel: ExpenseViewModel
    //Create late init variable of type Recycler View
    private lateinit var recyclerView: RecyclerView

    //Create late init variable of type text view
    private lateinit var current_total_expense: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)

        val actionBar = supportActionBar
        //Set the name of activity title
        actionBar!!.title = "Expense List"

        //Back to main activity
        actionBar.setDisplayHomeAsUpEnabled(true)

        //Link the recycler view variable with the expense_recyclerview item in activity_expense_list.xml
        recyclerView = findViewById(R.id.expense_recyclerview)

        //Link the textview variable with the xml textview in activity_expense_list.xml
        current_total_expense = findViewById(R.id.current_total_expense)

        val adapter = ExpenseListAdapter()

        //Set the recycler view adapter equal to the expense list adapter
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)

        //Display orientation set vertical ->display list down
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        //Set layout equal to recycler view layout
        recyclerView.layoutManager =  layoutManager

        //This will create ViewModels and retain them in a store of the ExpenseViewModel
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)

        //Read all the data and set the data in the view model
        mExpenseViewModel.readAllExpenseData.observe(this, Observer { expense ->
            adapter.setData(expense)
        })

        //Call the function to get the total expense
       getAndDisplayTotalExpense()

        /*Item touch helper to use as a delete item functionality
            Swipe left or right on the item will delete the item off the recycler
            delete from database
         */
        ItemTouchHelper( object :ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            //Move doesn't matter
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            //When item get swiped
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                //Create variable and store the viewholder position of swiped item
                val position = viewHolder.adapterPosition

                //Create an expense variable and call the get expense at position by using position as parameter
                val currentTarget: Expense = adapter.getExpenseAtPosition(position)

                //Call function to delete the current target expense item from database
                mExpenseViewModel.deleteExpense(currentTarget)

                //Toast to let user know that item is being deleted
                Toast.makeText(applicationContext, "Deleting expense ID:" + currentTarget.id, Toast.LENGTH_SHORT).show()

                //Let the adapter know that the dataset has been changed
                adapter.notifyDataSetChanged();

                //Let the adapter know that the income item at the swiped position got deleted
                adapter.notifyItemRemoved(position)

                //Get the new total after the deleting the item
                getAndDisplayTotalExpense()
            }
            //Attach the item touch helper to the recycler view
        }).attachToRecyclerView(recyclerView)

    }

    /*This function to being call by the oncreate function
       To call the query in the Expense DAO and return the total expense of all the item
       Display the total expense in the textview
     */
    private fun getAndDisplayTotalExpense(){
        /*
           Create a double variable and call the query to get the total expense of a variable
        */
        var sum: Double= mExpenseViewModel.getTotalExpense()
        //Format the double variable to 2 decimal place
        var totalExpense: String? = "%.2f".format(sum)

        //Update the textview
        current_total_expense.setText("Current Total: $$totalExpense")
    }
}
