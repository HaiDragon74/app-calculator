package com.voduchuy.appcalculator.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.voduchuy.appcalculator.adapter.CalculateAdapter
import com.voduchuy.appcalculator.model.Calculator
import com.voduchuy.appcalculator.HistoryViewModel
import com.voduchuy.appcalculator.HistoryViewModelFactory
import com.voduchuy.appcalculator.databinding.ActivityHistoryBinding
import com.voduchuy.appcalculator.room.CalculateDatabase

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var calculateAdapter: CalculateAdapter
    private lateinit var historyViewModel: HistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val historyViewModelFactory =
            HistoryViewModelFactory(CalculateDatabase.getCalculateDatabase(this))
        historyViewModel =
            ViewModelProvider(this, historyViewModelFactory)[HistoryViewModel::class.java]
        calculateAdapter = CalculateAdapter()
        binding.recyclerCalculator.layoutManager = LinearLayoutManager(this)
        binding.recyclerCalculator.adapter = calculateAdapter
        historyViewModel.realCalculate()
        historyViewModel.liveData.observe(this) {
            val list=it.reversed()
            calculateAdapter.setList(list as MutableList<Calculator>)
        }
        binding.btnBack.setOnClickListener {
            val intent = Intent(this, CalculateActivity::class.java)
            startActivity(intent)
        }
    }
}