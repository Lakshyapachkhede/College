package com.pachkhede.practiceexercise3

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var main: LinearLayout

    private lateinit var btnRed: Button
    private lateinit var btnGreen: Button
    private lateinit var btnBlue: Button
    private lateinit var btnYellow: Button
    private lateinit var btnReset: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main = findViewById(R.id.main)

        btnRed = findViewById(R.id.btnRed)
        btnGreen = findViewById(R.id.btnGreen)
        btnBlue = findViewById(R.id.btnBlue)
        btnYellow = findViewById(R.id.btnYello)
        btnReset = findViewById(R.id.btnReset)

        btnRed.setOnClickListener {
            main.setBackgroundColor(Color.RED)
        }

        btnGreen.setOnClickListener {
            main.setBackgroundColor(Color.GREEN)
        }

        btnBlue.setOnClickListener {
            main.setBackgroundColor(Color.BLUE)
        }

        btnYellow.setOnClickListener {
            main.setBackgroundColor(Color.YELLOW)
        }

        btnReset.setOnClickListener {
            main.setBackgroundColor(Color.WHITE)
        }
    }
}

