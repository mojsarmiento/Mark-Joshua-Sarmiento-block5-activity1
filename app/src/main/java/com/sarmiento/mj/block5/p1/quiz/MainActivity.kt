package com.sarmiento.mj.block5.p1.quiz

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calButton = findViewById<Button>(R.id.btnCalculate)

        calButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput(weight, height)){
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                //get result with two decimal places
                val bmi2digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2digits)
            }
        }
    }

    private fun validateInput(weight:String?, height:String?):Boolean{

        return when{
            weight.isNullOrEmpty()->{
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()->{
                Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show()
                return false
            }

            else -> {
                return true
            }
        }

    }

    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDescription = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "(normal range is 18.5 - 24.9)"

        var resultText = ""
        var color = 0

        when{
            bmi < 18.50 ->{
                resultText = "You Are Underweight"
                color = R.color.under_weight
            }
            bmi in 18.50..24.99->{
                resultText = "You Are Healthy"
                color = R.color.normal
            }
            bmi in 25.00..29.99->{
                resultText = "You are Overweight"
                color = R.color.over_weight
            }
            bmi >29.99->{
                resultText = "You are Obese"
                color = R.color.obese
            }

        }
        resultDescription.setTextColor(ContextCompat.getColor(this,color))
        resultDescription.text = resultText


    }
}