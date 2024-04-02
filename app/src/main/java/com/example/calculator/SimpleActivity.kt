package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class SimpleActivity : AppCompatActivity() {

    lateinit var currentInput: StringBuilder;
    lateinit var textPlace: TextView;
    private var currentOperation: MathOperation = MathOperation.NONE;
    private var insideSpace: Double = 0.0;
    private var isNextClear: Boolean = false;
    var buttonCCETimes: Int = 0;

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

        val buttonPlusMinus = findViewById<Button>(R.id.buttonPlusMinus)
        buttonPlusMinus.setOnClickListener {
            changeSign()
        }

        val buttonAdd = findViewById<Button>(R.id.buttonPlus)
        buttonAdd.setOnClickListener {
            setMathOperation(MathOperation.ADD)
        }

        val buttonMinus = findViewById<Button>(R.id.buttonMinus)
        buttonMinus.setOnClickListener {
            setMathOperation(MathOperation.SUBTRACT)
        }

        val buttonDivide = findViewById<Button>(R.id.buttonDivide)
        buttonDivide.setOnClickListener {
            setMathOperation(MathOperation.DIVIDE)
        }

        val buttonMultiply = findViewById<Button>(R.id.buttonMultiply)
        buttonMultiply.setOnClickListener {
            setMathOperation(MathOperation.MULTIPLY)
        }

        val buttonEqual = findViewById<Button>(R.id.buttonEquals)
        buttonEqual.setOnClickListener {
            getResult()
        }

        val buttonCCE = findViewById<Button>(R.id.buttonC)
        buttonCCE.setOnClickListener {
            if (buttonCCETimes == 0) {
                clearDisplay()
                buttonCCETimes = 1
            }
            else if (buttonCCETimes == 1) {
                handleClearAll()
                buttonCCETimes = 0
            }
        }

    }


    private fun buttonCCETimesToZero() {
        buttonCCETimes = 0
    }

    fun getResult() {
        buttonCCETimesToZero()
        var currentDigit = textPlace.text.toString().toDouble()
        when( this.currentOperation) {
            MathOperation.ADD -> currentDigit += this.insideSpace
            MathOperation.SUBTRACT -> {
                if (!isNextClear)
                    currentDigit = this.insideSpace - currentDigit
                else
                    currentDigit -= this.insideSpace
            }
            MathOperation.DIVIDE -> {
                if (textPlace.text.toString() == "0") {
                    Toast.makeText(applicationContext, "Nie można dzielić przez zero!",
                        Toast.LENGTH_SHORT).show()
                    return
                }
                if (!isNextClear)
                    currentDigit = this.insideSpace / currentDigit
                else
                    currentDigit /= this.insideSpace

            }
            MathOperation.MULTIPLY -> currentDigit *= this.insideSpace
            else -> {}
        }
        if (!isNextClear)
            this.insideSpace = textPlace.text.toString().toDouble()
        currentInput.clear()

        if (currentDigit.rem(1.0) == 0.0) {
            currentInput.insert(0, currentDigit.toInt().toString())
        }
        else
            currentInput.insert(0, currentDigit.toString())
        updateDisplay()
        isNextClear = true
    }

    fun setMathOperation(operation: MathOperation) {
        buttonCCETimesToZero()
        if (currentOperation !== MathOperation.NONE && !isNextClear) {
            getResult()
        }
        else {
            this.insideSpace = textPlace.text.toString().toDouble()
            isNextClear = true
        }
        currentOperation = operation
    }


    fun changeSign() {
        buttonCCETimesToZero()
        if (textPlace.text.toString() != "0") {
            val currentDisplay = textPlace.text.toString()
            if (currentDisplay.first() == '-') {
                currentInput.deleteCharAt(0)
                updateDisplay()
            }
            else {
                val temporaryDisplay = "-$currentDisplay"
                currentInput.clear()
                currentInput.insert(0, temporaryDisplay)
                updateDisplay()
            }
        }
    }

    fun clearDisplay() {
        currentInput.clear()
        appendNumber("0")
    }

    fun handleClearAll() {
        insideSpace = 0.0
        buttonCCETimesToZero()
        currentOperation = MathOperation.NONE
        isNextClear = false
        clearDisplay()
    }

    fun handleClear() {
        if (currentInput.isNotEmpty() && textPlace.text.toString() != "0") {
            currentInput.deleteCharAt(currentInput.length - 1)
            updateDisplay()
            if (textPlace.text.toString() == "-")
                currentInput.clear()
            if (currentInput.isEmpty())
                appendNumber("0")
        }
        else if (textPlace.text.toString() != "0")
            appendNumber("0")
    }

    private fun updateDisplay() {
        textPlace.text = currentInput.toString()
    }

    fun appendNumber(number: String) {
        buttonCCETimesToZero()
        if (isNextClear) {
            this.insideSpace = textPlace.text.toString().toDouble()
            currentInput.clear()
            isNextClear = false
        }
        if (textPlace.text.toString() == "0") {
            currentInput.clear()
        }
        currentInput.append(number)
        updateDisplay()
    }
}