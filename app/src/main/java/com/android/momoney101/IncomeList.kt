package com.android.momoney101

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.momoney101.data.IncomeViewModel
import com.android.momoney101.list.IncomeListAdapter

class IncomeList : AppCompatActivity() {

    private lateinit var mIncomeViewModel: IncomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_list)

        val actionBar = supportActionBar

        actionBar!!.title = "Income List"

        actionBar.setDisplayHomeAsUpEnabled(true)

        val recyclerView = findViewById<RecyclerView>(R.id.income_recyclerview)

        val adapter = IncomeListAdapter()
        recyclerView.adapter = adapter

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager =  layoutManager

        mIncomeViewModel = ViewModelProvider(this).get(IncomeViewModel::class.java)
        mIncomeViewModel.readAllIncomeData.observe(this, Observer { income ->
            adapter.setData(income)
        })
    }
}