package com.android.momoney101

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    //Create variables for linking after inflated
    private lateinit var NewExpense_btn: Button
    private lateinit var NewIncome_btn: Button


    lateinit var toggle: ActionBarDrawerToggle
    private lateinit var navView: NavigationView
    private lateinit var drawer:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Linking the buttons of the main activity
        NewExpense_btn =findViewById(R.id.add_new_monthly_expense_button)
        NewIncome_btn = findViewById(R.id.add_new_monthly_income_button)

        //Linking the drawer layout
        drawer= findViewById(R.id.drawer_Layout)

        //Linking the action bar toggle layout between the drawer view and main activity
        toggle = ActionBarDrawerToggle(this,drawer, R.string.open, R.string.close)

        //connect toggle with drawer layout
        drawer.addDrawerListener(toggle)

        //Sync the state of the toggle and ready to be used
        toggle.syncState()

        //Back arrow to close the navigation drawer
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Setting on click listeners for the two buttons.
        //This will start the new activity in New Expense
        NewExpense_btn.setOnClickListener {
            val intent_NewExpense = Intent(this,NewExpense::class.java)
            startActivity(intent_NewExpense)
        }
        //This will start the new activity in New Income
        NewIncome_btn.setOnClickListener {
            val invent_NewIncome = Intent(this,NewIncome::class.java)
            startActivity(invent_NewIncome)
        }

        //Link the navigation view
        navView = findViewById(R.id.nav_view)


        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.Expense_list ->{
                    val intent_ExpenseList = Intent(this,ExpenseList::class.java)
                    startActivity(intent_ExpenseList)
                }
                R.id.Income_list -> {
                    val intent_IncomeList = Intent(this,IncomeList::class.java)
                    startActivity(intent_IncomeList)
                }
            }
            true
        }
    }

    /*
    This function is used by the navigation drawer menu to override
       Linking with which item in the menu is being selected in the navigation drawer
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}