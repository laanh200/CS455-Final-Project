package com.android.momoney101.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.momoney101.R

import com.android.momoney101.data.Income

class IncomeListAdapter:RecyclerView.Adapter<IncomeListAdapter.MyViewHolder>() {

    //Create an empty list of income
    private var incomeList = emptyList<Income>()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //Create the view in the file activity_income_list
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.income_custom_row,parent, false))
    }

    //Function to return the size of the list
    override fun getItemCount(): Int {
        return incomeList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        //Create a variable and set to the current position of item in the list
        val currentItem = incomeList[position]

        //Assign the the current item data to the text view to display
        holder.itemView.findViewById<TextView>(R.id.income_item_id).setText(currentItem.id.toString())
        holder.itemView.findViewById<TextView>(R.id.income_item_amount).setText(currentItem.incomeAmount.toString())
        holder.itemView.findViewById<TextView>(R.id.income_item_date).setText(currentItem.incomeDate)
    }

    //Function to set the income parameter to the current income list
    fun setData(income: List<Income>){
        this.incomeList = income
        //Notify recyclerview for changes been made
        notifyDataSetChanged()
    }
}