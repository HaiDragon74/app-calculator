package com.voduchuy.appcalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalculateAdapter():RecyclerView.Adapter<CalculateAdapter.ViewHolder>() {
    private var lístCalculate= mutableListOf<Calculator>()
    fun setList(lístCalculate:MutableList<Calculator>){
        this.lístCalculate=lístCalculate
        notifyDataSetChanged()
    }
    class ViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView) {
        val tvCalculation=itemView.findViewById<TextView>(R.id.tv_calculation)
        val tvItemResult=itemView.findViewById<TextView>(R.id.tv_item_result)
        val tvTime=itemView.findViewById<TextView>(R.id.tv_time)
        fun bind(item:Calculator){
            tvCalculation.text=item.calculation
            tvItemResult.text= item.result.toString()
            tvTime.text= item.time.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_calculate,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        return lístCalculate.size
    }
}