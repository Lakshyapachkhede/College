package com.pachkhede.assignment4

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MethodActivity : AppCompatActivity() {

    private lateinit var tvName: TextView
    private lateinit var tvDesc: TextView
    private lateinit var tvEx: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_method)


        tvName = findViewById(R.id.tvName)
        tvDesc = findViewById(R.id.tvDesc)
        tvEx = findViewById(R.id.tvExample)

        tvName.text = intent.getStringExtra("name")
        tvDesc.text = intent.getStringExtra("description")
        tvEx.text = intent.getStringExtra("example")


    }
}