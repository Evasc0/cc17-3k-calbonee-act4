package com.example.tipcalculator

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val billAmount = findViewById<EditText>(R.id.billAmount)
        val tipOptions = findViewById<RadioGroup>(R.id.tipOptions)
        val roundUpSwitch = findViewById<Switch>(R.id.roundUpSwitch)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val tipResult = findViewById<TextView>(R.id.tipResult)

        calculateButton.setOnClickListener {
            val amount = billAmount.text.toString().toDoubleOrNull()
            if (amount == null || amount == 0.0) {
                tipResult.text = "Please enter a valid amount"
                return@setOnClickListener
            }

            val tipPercentage = when (tipOptions.checkedRadioButtonId) {
                R.id.tipOption20 -> 0.20
                R.id.tipOption18 -> 0.18
                else -> 0.15
            }

            var tip = amount * tipPercentage

            if (roundUpSwitch.isChecked) {
                tip = ceil(tip)
            }

            tipResult.text = "Tip Amount: $${"%.2f".format(tip)}"
        }
    }
}
