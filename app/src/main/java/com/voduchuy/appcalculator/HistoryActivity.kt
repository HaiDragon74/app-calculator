package com.voduchuy.appcalculator

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.voduchuy.appcalculator.databinding.ActivityHistoryBinding
import com.voduchuy.appcalculator.room.CalculateDatabase
import com.voduchuy.appcalculator.room.HistoryViewModel
import com.voduchuy.appcalculator.room.HistoryViewModelFactory

class HistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoryBinding
    private lateinit var calculateAdapter: CalculateAdapter
    private lateinit var historyViewModel: HistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val historyViewModelFactory= HistoryViewModelFactory(CalculateDatabase.getCalculateDatabase(this))
        historyViewModel= ViewModelProvider(this,historyViewModelFactory)[HistoryViewModel::class.java]
        calculateAdapter= CalculateAdapter()
        binding.recyclerCalculator.layoutManager=LinearLayoutManager(this)
        binding.recyclerCalculator.adapter=calculateAdapter
        historyViewModel.realCalculate().observe(this){
            calculateAdapter.setList(it as MutableList<Calculator>)
        }

    }
}