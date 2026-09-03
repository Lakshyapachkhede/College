package com.pachkhede.assignment3

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var listView : ListView
    lateinit var spinner : Spinner
    lateinit var food_name_tv : TextView
    lateinit var price_tv : TextView
    lateinit var order_btn : Button
    val categories = arrayOf("PIZZA","BURGER","SANDWICHES","DESSERT")
    val foodItems = arrayOf(
        // pizza
        arrayOf(
            FoodItem("Margherita Pizza", 199),
            FoodItem("Cheese Pizza", 249),
            FoodItem("Veggie Pizza", 229),
            FoodItem("Farmhouse Pizza", 299)
        ),

        // burger
        arrayOf(
            FoodItem("Classic Burger", 149),
            FoodItem("Cheese Burger", 179),
            FoodItem("Veggie Burger", 159),
            FoodItem("Double Patty Burger", 229)
        ),

        // sandwiches
        arrayOf(
            FoodItem("Veg Sandwich", 99),
            FoodItem("Cheese Sandwich", 129),
            FoodItem("Grilled Sandwich", 149),
            FoodItem("Club Sandwich", 179)
        ),

        // dessert
        arrayOf(
            FoodItem("Chocolate Cake", 129),
            FoodItem("Ice Cream Sundae", 99),
            FoodItem("Brownie", 119),
            FoodItem("Gulab Jamun", 89)
        )
    )
    val listData = mutableListOf<String>()

    fun initSpinner()
    {
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener
        {
            override fun onItemSelected(
                p0: AdapterView<*>?,
                p1: View?,
                position: Int,
                p3: Long
            ) {

                val selectedCategoryItems = foodItems[position]
                listData.clear()

                for (item in selectedCategoryItems)
                {
                    listData.add("${item.name} - Rs.${item.price}")
                }

                (listView.adapter as ArrayAdapter<String>).notifyDataSetChanged()


            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }

    fun initListView()
    {
        val listAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listData)
        listView.adapter = listAdapter;

        listView.setOnItemClickListener { parent, view, pos, id ->

            val catIdx = spinner.selectedItemPosition

            val item = foodItems[catIdx][pos]

            food_name_tv.text = "Food Name: ${item.name}"
            price_tv.text = "Price: Rs.${item.price}"

            order_btn.setOnClickListener {
                val i = Intent(applicationContext, Order::class.java)
                i.putExtra("NAME", item.name)
                i.putExtra("PRICE", item.price)
                startActivity(i)
            }
        }

    }

    fun initViews()
    {

        spinner = findViewById<Spinner>(R.id.my_spinner)
        listView = findViewById<ListView>(R.id.my_list_view)
        food_name_tv = findViewById<TextView>(R.id.food_name)
        price_tv = findViewById<TextView>(R.id.price)
        order_btn = findViewById<Button>(R.id.order_button)

        initSpinner()
        initListView()


    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()

    }
}