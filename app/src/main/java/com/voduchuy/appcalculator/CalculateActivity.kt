package com.voduchuy.appcalculator
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.voduchuy.appcalculator.databinding.ActivityCalculateBinding
import com.voduchuy.appcalculator.room.CalculateDatabase
import com.voduchuy.appcalculator.room.HistoryViewModel
import com.voduchuy.appcalculator.room.HistoryViewModelFactory

class CalculateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculateBinding
    private lateinit var historyViewModel: HistoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val historyViewModelFactory=HistoryViewModelFactory(CalculateDatabase.getCalculateDatabase(this))
        historyViewModel=ViewModelProvider(this,historyViewModelFactory)[HistoryViewModel::class.java]
        setCalculation()
        deleteCalculation()
        showResult()
    }

    private fun showResult() {
        binding.btnResult.setOnClickListener {
            val input = binding.edtCalculation.text.toString()
            val numbers = input.split("[+\\-x/]".toRegex()).map { it.toInt() }.toMutableList()
            val operators = input.split("\\d+".toRegex()).filter { it.isNotBlank() }
            var result = numbers.first()
            for (i in operators.indices) {
                val operator = operators[i]
                val nextNumber = numbers[i + 1]

                // Perform operation based on the current operator
                result = when (operator) {
                    "+" -> operationInt(CalculateOperation.ADDITION, result, nextNumber)
                    "-" -> operationInt(CalculateOperation.SUBTRACTION, result, nextNumber)
                    "x" -> operationInt(CalculateOperation.MULTIPLICATION, result, nextNumber)
                    "/" -> operationInt(CalculateOperation.DIVISION, result, nextNumber)
                    else -> {
                        // Handle unknown operators
                        Log.d("operators", "Unknown operator: $operator")
                        result // Keep the result unchanged
                    }
                }
            }
            binding.tvResult.text = result.toString()
            val time=System.currentTimeMillis()
            historyViewModel.insertCalculate(Calculator(calculation = input, result = result, time = time))
        }
    }

    private fun deleteCalculation() {
        binding.btnDelete.setOnClickListener {
            val calculation = binding.edtCalculation.text.toString()
            if (calculation.isNotEmpty()) {
                val currentText = calculation.length
                val newText = calculation.substring(0, currentText - 1)
                binding.edtCalculation.setText(newText)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setCalculation() {
        binding.btn0.setOnClickListener {
            val btn0 = binding.btn0.text.toString()
            binding.edtCalculation.append(btn0)
        }
        binding.btn1.setOnClickListener {
            val btn1 = binding.btn1.text.toString()
            binding.edtCalculation.append(btn1)
        }
        binding.btn2.setOnClickListener {
            val btn2 = binding.btn2.text.toString()
            binding.edtCalculation.append(btn2)
        }
        binding.btn3.setOnClickListener {
            val btn3 = binding.btn3.text.toString()
            binding.edtCalculation.append(btn3)
        }
        binding.btn4.setOnClickListener {
            val btn4 = binding.btn4.text.toString()
            binding.edtCalculation.append(btn4)
        }
        binding.btn5.setOnClickListener {
            val btn5 = binding.btn5.text.toString()
            binding.edtCalculation.append(btn5)
        }
        binding.btn6.setOnClickListener {
            val btn6 = binding.btn6.text.toString()
            binding.edtCalculation.append(btn6)
        }
        binding.btn7.setOnClickListener {
            val btn7 = binding.btn7.text.toString()
            binding.edtCalculation.append(btn7)
        }
        binding.btn8.setOnClickListener {
            val btn8 = binding.btn8.text.toString()
            binding.edtCalculation.append(btn8)
        }
        binding.btn9.setOnClickListener {
            val btn9 = binding.btn9.text.toString()
            binding.edtCalculation.append(btn9)
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
    }

    private fun operationInt(
        calculateOperation: CalculateOperation,
        number1: Int,
        number2: Int
    ): Int {
        return when (calculateOperation) {
            CalculateOperation.ADDITION -> number1 + number2
            CalculateOperation.SUBTRACTION -> number1 - number2
            CalculateOperation.MULTIPLICATION -> number1 * number2
            CalculateOperation.DIVISION -> number1 / number2
        }
    }

    private fun operationDouble(
        calculateOperation: CalculateOperation,
        number1: Double,
        number2: Double
    ): Double {
        return when (calculateOperation) {
            CalculateOperation.ADDITION -> number1 + number2
            CalculateOperation.SUBTRACTION -> number1 - number2
            CalculateOperation.MULTIPLICATION -> number1 * number2
            CalculateOperation.DIVISION -> number1 / number2
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