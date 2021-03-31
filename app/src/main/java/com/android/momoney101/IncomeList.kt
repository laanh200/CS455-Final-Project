package com.android.momoney101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.momoney101.viewmodel.IncomeViewModel
import com.android.momoney101.list.IncomeListAdapter
import com.android.momoney101.model.Income

class IncomeList : AppCompatActivity() {

    //Create late init variable of type incomeViewModel
    private lateinit var mIncomeViewModel: IncomeViewModel
    //Create late init variable of type Recycler View
    private lateinit var recyclerView: RecyclerView

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

                //Create an income variable and call the get Income at position by using position as parameter
                val currentTarget: Income = adapter.getIncomeAtPosition(position)

                //Call function to delete the current target income item from database
                mIncomeViewModel.deleteIncome(currentTarget)

                //Toast to let user know that item is being deleted
                Toast.makeText(applicationContext, "Deleting income ID:" + currentTarget.id, Toast.LENGTH_SHORT).show()

                //Let the adapter know that the dataset has been changed
                adapter.notifyDataSetChanged();

                //Let the adapter know that the income item at the swiped position got deleted
                adapter.notifyItemRemoved(position)
            }
            //Attach the item touch helper to the recycler view
        }).attachToRecyclerView(recyclerView)
    }
}