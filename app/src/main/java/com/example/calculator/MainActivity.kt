package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val closeApplicationBtn = findViewById<Button>(R.id.idBtnCloseApplication)

        closeApplicationBtn.setOnClickListener {
            this.finish()
            super.onDestroy()
            exitProcess(0)
        }

        val buttonSimpleClick = findViewById<Button>(R.id.simple)
        buttonSimpleClick.setOnClickListener {
            val intent = Intent(this, SimpleActivity::class.java)
            this.finish()
            startActivity(intent)
        }

        val buttonScientificClick = findViewById<Button>(R.id.scientific)
        buttonScientificClick.setOnClickListener {
            val intent = Intent(this, ScientificActivity::class.java)
            this.finish()
            startActivity(intent)
        }

        val buttonInfoClick = findViewById<Button>(R.id.idBtnInfo)
        buttonInfoClick.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java)
            this.finish()
            startActivity(intent)
        }
    }
}