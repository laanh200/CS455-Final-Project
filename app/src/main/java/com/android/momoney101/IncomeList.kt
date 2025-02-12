package com.android.momoney101

import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Update
import com.android.momoney101.viewmodel.IncomeViewModel
import com.android.momoney101.list.IncomeListAdapter
import com.android.momoney101.model.Income

class IncomeList : AppCompatActivity() {

    //Create late init variable of type incomeViewModel
    private lateinit var mIncomeViewModel: IncomeViewModel

    //Create late init variable of type Recycler View
    private lateinit var recyclerView: RecyclerView

    //Create a late init variable of type Text View
    private lateinit var current_total_income:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_list)

        val actionBar = supportActionBar

        //Set the name of activity title
        actionBar!!.title = "Income List"

        //Back to main activity
        actionBar.setDisplayHomeAsUpEnabled(true)

        //Link the recycler view variable with the income_recyclerview item in activity_income_list.xml
        recyclerView = findViewById(R.id.income_recyclerview)

        //Link the textview variable with the xml textview in activity_expense_list.xml
        current_total_income = findViewById(R.id.current_total_income)

        val adapter = IncomeListAdapter()

        //Set the recycler view adapter equal to the income list adapter
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)

        //Display orientation set vertical ->display list down
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        //Set layout equal to recycler view layout
        recyclerView.layoutManager =  layoutManager

        //This will create ViewModels and retain them in a store of the incomeViewModel
        mIncomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)

        //Read all the data and set the data in the view model
        mIncomeViewModel.readAllIncomeData.observe(this, Observer { income ->
            adapter.setData(income)
        })

        //Display the total income in the list
        getAndDisplayTotalIncome()

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

                //Create an alert dialog when the user swipe on an item
                val builder = AlertDialog.Builder(this@IncomeList)
                builder.setTitle("Delete income?")
                builder.setMessage("Are you sure you want to delete the current income item?").setCancelable(false)
                //If they select the yes option
                builder.setPositiveButton("Yes") { _, _ ->
                    //Call function to delete the swiped item
                    deleteItem(viewHolder, adapter)
                }
                //If the select the no option
                builder.setNegativeButton("No") { _, _ ->
                    //reload the adapter
                    adapter.notifyDataSetChanged();
                    //Get the target item being swiped
                    var itemTarget: View = viewHolder.itemView
                    //When they are swiped but answered no then turn back the background
                    itemTarget.setBackgroundColor(0)
                    itemTarget.setBackgroundResource(R.drawable.layout_border)
                }
                //Build the alert dialog and display
                builder.create().show()
            }
                override fun onChildDraw(c: Canvas, recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
                    //Create a value that hold the original position of item
                    val originalPosition:Float = 0.0F
                    //Get the target item
                    var itemTarget: View = viewHolder.itemView
                    if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE && isCurrentlyActive) {
                        if (dX != originalPosition){
                            //Set the background to red
                            itemTarget.setBackgroundColor(resources.getColor(R.color.red))
                        }
                    }else{
                        //If they release the swipe then turn the background back to default
                        itemTarget.setBackgroundColor(0)
                        itemTarget.setBackgroundResource(R.drawable.layout_border)
                    }
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
                }

            //Attach the item touch helper to the recycler view
        }).attachToRecyclerView(recyclerView)
    }

    //This function is used to call to delete the income item in the incomeList database
    private fun deleteItem(viewHolder: RecyclerView.ViewHolder, adapter: IncomeListAdapter){
        //Create variable and store the viewholder position of swiped item
        val position = viewHolder.adapterPosition

        //Create an income variable and call the get Income at position by using position as parameter
        val currentTarget: Income = adapter.getIncomeAtPosition(position)

        //Call function to delete the current target income item from database
        mIncomeViewModel.deleteIncome(currentTarget)

        //Toast to let user know that item is being deleted
        Toast.makeText(this, "Deleting income ID:" + currentTarget.id, Toast.LENGTH_SHORT).show()

        //Let the adapter know that the dataset has been changed
     //   adapter.notifyDataSetChanged();

        //Let the adapter know that the income item at the swiped position got deleted
        adapter.notifyItemRemoved(position)

        //Update the list total after deletion
        getAndDisplayTotalIncome()
    }

    //This function is used to call to delete all the income item in the incomeList database
    private fun deleteAllItems(){

        //Confirm with the user
        val builder = AlertDialog.Builder(this@IncomeList)
        builder.setTitle("Delete all income?")
        builder.setMessage("Are you sure you want to deletes all the current income items?").setCancelable(false)
        //If they select the yes option
        builder.setPositiveButton("Yes"){ _, _ ->
            //Call function to delete the swiped item
            mIncomeViewModel.deleteAllIncomes()
            Toast.makeText(this,"All expense items are now deleted.", Toast.LENGTH_SHORT)
            getAndDisplayTotalIncome()
        }
        //If the select the no option
        builder.setNegativeButton("No"){ _, _ ->
        }
        //create and show the dialog alert
        builder.create().show()
    }

    /*This function to being call by the oncreate function
       To call the query in the income DAO and return the total income of all the item
       Display the total income in the textview
     */
    private fun getAndDisplayTotalIncome(){
        /*
           Create a double variable and call the query to get the total expense of a variable
        */
        var sum: Double= mIncomeViewModel.getTotalIncome()
        //Format the double variable to 2 decimal place
        var totalIncome: String? = "%.2f".format(sum)

        //Update the textview
        current_total_income.setText("Current Total: $$totalIncome")
    }

    //Adding the list menu for deleting all the items
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //Create inflater for menu inflatoer
        val inflater = menuInflater
        //Inflate the menu
        inflater.inflate(R.menu.list_menu,menu)

        return super.onCreateOptionsMenu(menu)
    }

    //Function to check when user select on the menu item
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all_option ->{
                //Call function to delete all income item
                deleteAllItems()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}