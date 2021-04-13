package com.android.momoney101.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.momoney101.R
import com.android.momoney101.model.Expense


class ExpenseListAdapter:RecyclerView.Adapter<ExpenseListAdapter.MyViewHolder>() {

    //Create an empty list of expense
    private var expenseList = emptyList<Expense>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder {
        //Create the view in the file activity_income_list
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.expense_custom_row, parent, false)
        )
    }

    //Function to return the size of the list
    override fun getItemCount(): Int {
        return expenseList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Assign the current item to be the item position in the list
        val currentItem = expenseList[position]

        //Set the list item to be database item
        holder.itemView.findViewById<TextView>(R.id.expense_item_id).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.expense_item_amount).text = "$" + currentItem.expenseAmount.toString()
        holder.itemView.findViewById<TextView>(R.id.expense_item_date).text = currentItem.expenseDate
        holder.itemView.findViewById<TextView>(R.id.expense_item_name).text = currentItem.expenseName
        holder.itemView.findViewById<TextView>(R.id.expense_item_type).text = currentItem.expenseType
    }

    //Function to set the income parameter to the current expense list
    fun setData(expense: List<Expense>){
        this.expenseList =expense
        //Notify recyclerview for changes been made
        notifyDataSetChanged()
    }

    //Function to be called by the Expenselist.kt
    //Take the position of the expense in the adapterview
    //Return the expense item in the expense list
    fun getExpenseAtPosition(position: Int):Expense{
        //Return the expense item in the expense list
        return expenseList[position]
    }

}


