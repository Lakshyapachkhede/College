package com.pachkhede.assignment3


import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Order : AppCompatActivity() {
    lateinit var name_tv : TextView
    lateinit var price_tv : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_order)
        name_tv = findViewById<TextView>(R.id.food_name)
        price_tv = findViewById<TextView>(R.id.price)

        name_tv.text = intent.getStringExtra("NAME")
        price_tv.text = "Rs. ${intent.getIntExtra("PRICE", 0)}"



    }
}