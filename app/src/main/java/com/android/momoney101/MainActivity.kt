package com.android.momoney101

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatDelegate
import androidx.drawerlayout.widget.DrawerLayout

import com.google.android.material.navigation.NavigationView


class MainActivity : AppCompatActivity() {

    //Create variables for linking after inflated
    //Buttons type
    private lateinit var NewExpense_btn: Button
    private lateinit var NewIncome_btn: Button

    //Attaction bar drawer toggle type
    private lateinit var toggle: ActionBarDrawerToggle

    //Navigation view
    private lateinit var navView: NavigationView

    //Drawer Layout
    private lateinit var drawer:DrawerLayout

    //Drawer menu item
    private lateinit var menuItem: MenuItem

    //Switch in the menu item
    private lateinit var darkMode: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Linking the buttons of the main activity
        NewExpense_btn =findViewById(R.id.add_new_expense_button)
        NewIncome_btn = findViewById(R.id.add_new_income_button)

        //Linking the drawer layout in the main activity xml
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

        //Add a listener on the menu item being selected by user
        navView.setNavigationItemSelectedListener {
            //itemId is based on the menu item in nav_view_drawer_menu.xml that is in the res/menu directory
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

        //Link the switch in the navigation menu and the variable menu item
        menuItem = navView.menu.findItem(R.id.dark_mode_switch_item)

        //Link with the switch that is inside the menu item
        darkMode = menuItem.actionView.findViewById(R.id.dark_mode_switch)

        //Create a share preference variable to save the state of the switch
        val sharedPreferences: SharedPreferences = getSharedPreferences("value", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        //First when the switch is off then share preference value is false
        darkMode.isChecked = (sharedPreferences.getBoolean("value",false))

        if(darkMode.isChecked){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        //Assign change listener to the switch
        darkMode.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                //If the switch turn on then
                true -> {
                    //Change the theme to dark
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    //Change the value of the share preference variable to true
                    editor.putBoolean("value",true)
                }
                //If the switch turn off then
                false -> {
                    //Change the theme to regular
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    //Change the value of the share preference variable to false
                    editor.putBoolean("value",false)
                }
            }
            editor.apply()
        }
    }

    /*
    This function is used by the navigation drawer menu to override
       Linking with which item in the menu is being selected in the navigation drawer
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //Return true if the menu item is being selected
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}