package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    lateinit var closeApplicationBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        closeApplicationBtn = findViewById(R.id.idBtnCloseApplication)
        val activity: MainActivity = MainActivity()

        closeApplicationBtn.setOnClickListener {
            activity.finish()
            System.exit(0)
        }

        val buttonSimpleClick = findViewById<Button>(R.id.simple)
        buttonSimpleClick.setOnClickListener {
            val intent = Intent(this, SimpleActivity::class.java)
            startActivity(intent)
        }

        val buttonScientificClick = findViewById<Button>(R.id.scientific)
        buttonScientificClick.setOnClickListener {
            val intent = Intent(this, ScientificActivity::class.java)
            startActivity(intent)
        }
    }
}