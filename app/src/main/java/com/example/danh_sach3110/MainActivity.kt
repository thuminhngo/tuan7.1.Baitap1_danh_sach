package com.example.danh_sach3110

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editTextNumber = findViewById<EditText>(R.id.editText)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val buttonShow = findViewById<Button>(R.id.buttonShow)
        val listView = findViewById<ListView>(R.id.listView)
        val textViewError = findViewById<TextView>(R.id.textViewError)

        buttonShow.setOnClickListener {
            val input = editTextNumber.text.toString()
            if (input.isEmpty()) {
                textViewError.text = "Please enter a number"
                textViewError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            val n = input.toIntOrNull()
            if (n == null || n <= 0) {
                textViewError.text = "Please enter a valid positive integer"
                textViewError.visibility = TextView.VISIBLE
                return@setOnClickListener
            }

            textViewError.visibility = TextView.GONE
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val numbers = when (selectedRadioButtonId) {
                R.id.sochan -> (0..n).filter { it % 2 == 0 }
                R.id.sole -> (0..n).filter { it % 2 != 0 }
                R.id.sochinhpuong -> (0..n).filter { Math.sqrt(it.toDouble()).toInt() * Math.sqrt(it.toDouble()).toInt() == it }
                else -> emptyList()
            }

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }
}