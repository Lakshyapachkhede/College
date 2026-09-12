package com.pachkhede.practiceexercise2

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var input: EditText
    private lateinit var status: TextView
    private lateinit var btn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input = findViewById(R.id.input)
        status = findViewById(R.id.result)
        btn = findViewById(R.id.btn)
        var win = false

        var value = (1..10).random()
        var tries = 0

        btn.setOnClickListener {

            if (win) {
                input.text.clear()
                value = (1..10).random()
                status.text = "Enter number between 1 and 10:"
                win = false
                btn.text = "Check"
                tries = 0

                return@setOnClickListener
            }

            val guess = input.text.toString().toIntOrNull()

            if (guess == null) {
                status.text = "Please enter a valid number"
                return@setOnClickListener
            }


            tries++
            input.text.clear()


            if (value > guess) {
                status.text = "Try Again! Too Low"
            } else if (value < guess) {
                status.text = "Try Again! Too High"
            } else {
                status.text = "You Win in $tries tries! Play Again?"
                btn.text = "Play Again"
                win = true
            }


        }


    }
}