package com.android.momoney101.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.momoney101.R
import com.android.momoney101.data.Expense



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
        holder.itemView.findViewById<TextView>(R.id.expense_item_id).setText(currentItem.id.toString())
        holder.itemView.findViewById<TextView>(R.id.expense_item_amount).setText(currentItem.expenseAmount.toString())
        holder.itemView.findViewById<TextView>(R.id.expense_item_date).setText(currentItem.expenseDate)
        holder.itemView.findViewById<TextView>(R.id.expense_item_name).setText(currentItem.expenseName)
        holder.itemView.findViewById<TextView>(R.id.expense_item_type).setText(currentItem.expenseType)
    }

    //Function to set the income parameter to the current expense list
    fun setData(expense: List<Expense>){
        this.expenseList =expense
        //Notify recyclerview for changes been made
        notifyDataSetChanged()
    }

}