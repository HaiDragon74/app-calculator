package com.voduchuy.appcalculator.activity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.voduchuy.appcalculator.model.Calculator
import com.voduchuy.appcalculator.HistoryViewModel
import com.voduchuy.appcalculator.HistoryViewModelFactory
import com.voduchuy.appcalculator.databinding.ActivityCalculateBinding
import com.voduchuy.appcalculator.room.CalculateDatabase

class CalculateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculateBinding
    private lateinit var historyViewModel: HistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val historyViewModelFactory =
            HistoryViewModelFactory(CalculateDatabase.getCalculateDatabase(this))
        historyViewModel =
            ViewModelProvider(this, historyViewModelFactory)[HistoryViewModel::class.java]
        setCalculation()
        deleteCalculation()
        showResult()
        binding.btnHistory.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
        }
        val sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
        val valueCalculation = sharedPreferences.getString("calculation", "")
        val valueResult = sharedPreferences.getString("result", "")
        binding.edtCalculation.setText(valueCalculation)
        binding.tvResult.text = valueResult
    }

    private fun showResult() {
        binding.btnResult.setOnClickListener {
            val input = binding.edtCalculation.text.toString()
            if (input.last() == '+' || input.last() == '-' || input.last() == 'x' || input.last() == '/') {
                Toast.makeText(this, "Phep tinh chua dung", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val numbers = input.split("[+\\-x/]".toRegex()).map { it.toDouble() }.toMutableList()
            val operators = input.split("\\d+".toRegex()).filter { it.isNotBlank() }
            var resultToInt = ""
            var result = numbers[0]
            for (i in 1 until numbers.size) {
                val operator = operators[i - 1]
                val number = numbers[i]
                result = when (operator) {
                    "+" -> result + number
                    "-" -> result - number
                    "x" -> result * number
                    "/" -> {
                        if (number.toInt() == 0) {
                           Toast.makeText(this,"Phep tinh khong ton tai",Toast.LENGTH_SHORT).show()
                            return@setOnClickListener
                        } else {
                            result / number
                        }
                    }
                    else -> result
                }
            }
            if (result.toString().endsWith(".0")) {
                resultToInt = result.toString().substring(0, result.toString().length - 2)
            } else {
                resultToInt = result.toString()
            }
            val time = System.currentTimeMillis()
            val sharedPreferences = getSharedPreferences("sharedPreferences", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putString("calculation", input)
            editor.putString("result", resultToInt)
            editor.apply()
            if (numbers[0].toInt() == 0) {
                if (input.last() == 'x' || input.last() == '/') {
                    binding.tvResult.text = ""
                } else
                    binding.tvResult.text = resultToInt
            } else if (numbers[0].toInt() == 0) {
                binding.tvResult.text = resultToInt
            } else
                binding.tvResult.text = resultToInt

            historyViewModel.insertCalculate(
                Calculator(
                    calculation = input,
                    result = resultToInt,
                    time = time.toString(),
                    itemID = 0
                )
            )
        }
    }
    private fun deleteCalculation() {
        binding.btnDelete.setOnClickListener {
            val calculation = binding.edtCalculation.text.toString()
            if (calculation.isNotEmpty()) {
                val currentText = calculation.length
                val newText = calculation.substring(0, currentText - 1)
                binding.edtCalculation.setText(newText)
                binding.tvResult.text = ""
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCalculation() {
        binding.btnZero.setOnClickListener {
            val btnZero = binding.btnZero.text.toString()
            binding.edtCalculation.append(btnZero)
        }
        binding.btnOne.setOnClickListener {
            val btnOne = binding.btnOne.text.toString()
            binding.edtCalculation.append(btnOne)
        }
        binding.btnTow.setOnClickListener {
            val btnTow = binding.btnTow.text.toString()
            binding.edtCalculation.append(btnTow)
        }
        binding.btnThree.setOnClickListener {
            val btnThree = binding.btnThree.text.toString()
            binding.edtCalculation.append(btnThree)
        }
        binding.btnFour.setOnClickListener {
            val btnFour = binding.btnFour.text.toString()
            binding.edtCalculation.append(btnFour)
        }
        binding.btnFive.setOnClickListener {
            val btnFive = binding.btnFive.text.toString()
            binding.edtCalculation.append(btnFive)
        }
        binding.btnSix.setOnClickListener {
            val btnSix = binding.btnSix.text.toString()
            binding.edtCalculation.append(btnSix)
        }
        binding.btnSeven.setOnClickListener {
            val btnSeven = binding.btnSeven.text.toString()
            binding.edtCalculation.append(btnSeven)
        }
        binding.btnEight.setOnClickListener {
            val btnEight = binding.btnEight.text.toString()
            binding.edtCalculation.append(btnEight)
        }
        binding.btnNine.setOnClickListener {
            val btnNine = binding.btnNine.text.toString()
            binding.edtCalculation.append(btnNine)
        }
        binding.btnAddition.setOnClickListener {
            onClickCalculation('+')
        }
        binding.btnSubtraction.setOnClickListener {
            onClickCalculation('-')
        }
        binding.btnMultiplication.setOnClickListener {
            onClickCalculation('x')
        }
        binding.btnDivision.setOnClickListener {
            onClickCalculation('/')
        }
        binding.btnDecimal.setOnClickListener {
            onClickCalculation('.')
        }

    }

    private fun onClickCalculation(char: Char) {
        val currentText = binding.edtCalculation.text.toString()
        if (currentText.isNotEmpty()) {
            val lastChar = currentText.last()
            if (lastChar == '+' || lastChar == '-' || lastChar == 'x' || lastChar == '/') {
                val newText = currentText.substring(0, currentText.length - 1) + char
                binding.edtCalculation.setText(newText)
            } else {
                val newText = currentText.substring(0, currentText.length) + char
                binding.edtCalculation.setText(newText)
            }

        }
    }
}