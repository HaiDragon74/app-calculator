package com.voduchuy.appcalculator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.voduchuy.appcalculator.R
import com.voduchuy.appcalculator.model.Calculator
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

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
        fun bind(item: Calculator){
            tvCalculation.text=item.calculation
            tvItemResult.text= item.result.toString()
            tvTime.text= item.time?.toLong()?.let { simpeleTime(it) }
        }
        private fun simpeleTime(time:Long):String{
            val calendar= Calendar.getInstance()
            val date=Date(time)
            calendar.time=date
            val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("EEEE dd/MM/yyyy", Locale.getDefault())
            return simpleDateFormat.format(Date(time))
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_calculate,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(lístCalculate[position])
    }

    override fun getItemCount(): Int {
        return lístCalculate.size
    }

}