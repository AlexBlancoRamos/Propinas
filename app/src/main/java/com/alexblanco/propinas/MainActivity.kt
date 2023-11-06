package com.alexblanco.propinas

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    @SuppressLint("StringFormatInvalid")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val costOfServiceEditText = findViewById<EditText>(R.id.cost_of_service)
        val tipOptionsRadioGroup = findViewById<RadioGroup>(R.id.tip_options)
        val roundUpSwitch = findViewById<Switch>(R.id.round_up_switch)
        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val tipResultTextView = findViewById<TextView>(R.id.tip_result)

        calculateButton.setOnClickListener {
            val costOfService = costOfServiceEditText.text.toString().toDoubleOrNull() ?: 0.0
            val selectedRadioButton = findViewById<RadioButton>(tipOptionsRadioGroup.checkedRadioButtonId)
            val tipPercentage = when (selectedRadioButton) {
                findViewById<RadioButton>(R.id.option_twenty_percent) -> 0.20
                findViewById<RadioButton>(R.id.option_eighteen_percent) -> 0.18
                findViewById<RadioButton>(R.id.option_fifteen_percent) -> 0.15
                else -> 0.0
            }

            var tipAmount = costOfService * tipPercentage

            if (roundUpSwitch.isChecked) {
                tipAmount = kotlin.math.ceil(tipAmount)
            }

            val totalAmount = costOfService + tipAmount

            tipResultTextView.text = getString(R.string.tip_amount_result, tipAmount, totalAmount)


        }
    }
}