package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Math.pow
import kotlin.math.*

class ScientificActivity : SimpleActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scientific)

//        val buttonBackClick = findViewById<Button>(R.id.backButton)
//        buttonBackClick.setOnClickListener {
//            val intent = Intent(this, MainActivity::class.java)
//            this.finish()
//            startActivity(intent)
//        }

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

        val buttonSine = findViewById<Button>(R.id.buttonSin)
        buttonSine.setOnClickListener {
            asapExecutiveMath(ExpressMathOperation.SINE)
        }

        val buttonCosine = findViewById<Button>(R.id.buttonCos)
        buttonCosine.setOnClickListener {
            asapExecutiveMath(ExpressMathOperation.COSINE)
        }

        val buttonTang = findViewById<Button>(R.id.buttonTan)
        buttonTang.setOnClickListener {
            asapExecutiveMath(ExpressMathOperation.TANGENT)
        }

        val buttonSqrt = findViewById<Button>(R.id.buttonSqrt)
        buttonSqrt.setOnClickListener {
            asapExecutiveMath(ExpressMathOperation.SQRT)
        }

        val buttonPercent = findViewById<Button>(R.id.buttonPercent)
        buttonPercent.setOnClickListener {
            setMathOperation(MathOperation.PERCENT)
        }

        val buttonFactorial = findViewById<Button>(R.id.buttonFactorial)
        buttonFactorial.setOnClickListener {
            asapExecutiveMath(ExpressMathOperation.FACTORIAL)
        }

        val buttonToSquare = findViewById<Button>(R.id.buttonToSquare)
        buttonToSquare.setOnClickListener {
            asapExecutiveMath(ExpressMathOperation.SQUARE)
        }

        val buttonPower = findViewById<Button>(R.id.buttonPower)
        buttonPower.setOnClickListener {
            setMathOperation(MathOperation.POWER)
        }

        val buttonLog = findViewById<Button>(R.id.buttonLog)
        buttonLog.setOnClickListener {
            asapExecutiveMath(ExpressMathOperation.L0GARITHM)
        }

        val buttonLn = findViewById<Button>(R.id.buttonLn)
        buttonLn.setOnClickListener {
            asapExecutiveMath(ExpressMathOperation.NATURAL_L0GARITHM)
        }

    }

    private fun factorialRecursive(n: Int): Double {
        require(n >= 0) { "n must be positive" }
        return if (n <= 1) {
            1.0
        } else {
            n * factorialRecursive(n - 1)
        }
    }

    private fun asapExecutiveMath(operation: ExpressMathOperation) {
        buttonCCETimesToZero()
        val currentDigit = textPlace.text.toString().toDouble()
        val result: Double = when(operation) {
            ExpressMathOperation.SINE -> sin(currentDigit)
            ExpressMathOperation.COSINE -> cos(currentDigit)
            ExpressMathOperation.TANGENT -> tan(currentDigit)
            ExpressMathOperation.SQRT -> {
                if (textPlace.text.toString().toDouble() < 0 ) {
                    Toast.makeText(applicationContext, "Nie można pierwiastkować liczb ujemnych!",
                        Toast.LENGTH_SHORT).show()
                    return
                }
                sqrt(currentDigit)
            }
            ExpressMathOperation.FACTORIAL -> {
                if (textPlace.text.toString().toDouble().rem(1.0) != 0.0 || textPlace.text.toString().toDouble() < 0 ) {
                    Toast.makeText(applicationContext, "Niepoprawna liczba!",
                        Toast.LENGTH_SHORT).show()
                    return
                }
                factorialRecursive(currentDigit.toInt())
            }
            ExpressMathOperation.SQUARE -> currentDigit*currentDigit
            ExpressMathOperation.NATURAL_L0GARITHM -> ln(currentDigit)
            ExpressMathOperation.L0GARITHM -> log10(currentDigit)
        }
        currentInput.clear()

        if (result.rem(1.0) == 0.0) {
            currentInput.insert(0, result.toInt().toString())
        }
        else
            currentInput.insert(0, result.toString())
        updateDisplay()
        isNextClear = true
    }
}