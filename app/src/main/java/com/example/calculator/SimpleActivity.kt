package com.example.calculator

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SimpleActivity : AppCompatActivity() {

    private lateinit var currentInput: StringBuilder;
    private lateinit var textPlace: TextView;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple)

        val buttonBackClick = findViewById<Button>(R.id.backButton)
        buttonBackClick.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        this.textPlace = findViewById<TextView>(R.id.textResultField)
        this.currentInput = StringBuilder()

        val button0 = findViewById<Button>(R.id.button0)
        button0.setOnClickListener {
            if (textPlace.text.toString() != "0")
                appendNumber("0")
        }

        val button1 = findViewById<Button>(R.id.button1)
        button1.setOnClickListener {
            appendNumber("1")
        }

        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            appendNumber("2")
        }

        val button3 = findViewById<Button>(R.id.button3)
        button3.setOnClickListener {
            appendNumber("3")
        }

        val button4 = findViewById<Button>(R.id.button4)
        button4.setOnClickListener {
            appendNumber("4")
        }

        val button5 = findViewById<Button>(R.id.button5)
        button5.setOnClickListener {
            appendNumber("5")
        }

        val button6 = findViewById<Button>(R.id.button6)
        button6.setOnClickListener {
            appendNumber("6")
        }

        val button7 = findViewById<Button>(R.id.button7)
        button7.setOnClickListener {
            appendNumber("7")
        }

        val button8 = findViewById<Button>(R.id.button8)
        button8.setOnClickListener {
            appendNumber("8")
        }

        val button9 = findViewById<Button>(R.id.button9)
        button9.setOnClickListener {
            appendNumber("9")
        }

        val buttonDot = findViewById<Button>(R.id.buttonDot)
        buttonDot.setOnClickListener {
            val count = textPlace.text.toString().count { it == '.' }
            if (count == 0) {
                if (textPlace.text.toString() == "0" || textPlace.text.toString() == "")
                    appendNumber("0.")
                else
                    appendNumber(".")
            }
        }

        val buttonClear = findViewById<Button>(R.id.buttonClear)
        buttonClear.setOnClickListener {
            handleClear()
        }

        val buttonClearAll = findViewById<Button>(R.id.buttonCA)
        buttonClearAll.setOnClickListener {
            handleClearAll()
        }


    }

    private fun handleClearAll() {
        currentInput.clear()
        appendNumber("0")
    }

    private fun handleClear() {
        if (currentInput.isNotEmpty() && textPlace.text.toString() != "0") {
            currentInput.deleteCharAt(currentInput.length - 1)
            updateDisplay()
            if (currentInput.isEmpty())
                appendNumber("0")
        }
        else if (textPlace.text.toString() != "0")
            appendNumber("0")
    }

    private fun updateDisplay() {
        textPlace.text = currentInput.toString()
    }

    private fun appendNumber(number: String) {
        if (textPlace.text.toString() == "0") {
            currentInput.clear()
        }
        currentInput.append(number)
        updateDisplay()
    }
}