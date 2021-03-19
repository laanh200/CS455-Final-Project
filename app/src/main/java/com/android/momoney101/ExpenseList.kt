package com.android.momoney101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.momoney101.data.ExpenseViewModel
import com.android.momoney101.list.ExpenseListAdapter

class ExpenseList : AppCompatActivity() {

    private lateinit var mExpenseViewModel: ExpenseViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_expense_list)

        val actionBar = supportActionBar

        actionBar!!.title = "Expense List"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.expense_recyclerview)

        val adapter = ExpenseListAdapter()
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager =  layoutManager
        mExpenseViewModel = ViewModelProvider(this).get(ExpenseViewModel::class.java)
        mExpenseViewModel.readAllExpenseData.observe(this, Observer { expense ->
            adapter.setData(expense)
        })


    }
}