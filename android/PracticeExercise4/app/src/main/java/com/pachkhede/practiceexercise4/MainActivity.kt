package com.pachkhede.practiceexercise4

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    var data = mutableListOf<Student>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        data.add(Student("Rahul Sharma", 101, 20, "CSE"))
        data.add(Student("Priya Verma", 102, 19, "IT"))
        data.add(Student("Aman Gupta", 103, 21, "CSE"))
        data.add(Student("Sneha Patel", 104, 20, "ECE"))
        data.add(Student("Rohit Singh", 105, 19, "CSE"))
        data.add(Student("Neha Jain", 106, 20, "IT"))
        data.add(Student("Arjun Mehta", 107, 21, "CSE"))
        data.add(Student("Anjali Yadav", 108, 19, "ECE"))
        data.add(Student("Vikas Mishra", 109, 20, "CSE"))
        data.add(Student("Pooja Tiwari", 110, 21, "IT"))

        listView = findViewById(R.id.listView)

        val adapter = ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, data)
        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->

            val student = data[position]

            val intent = Intent(this, MainActivity2::class.java)

            intent.putExtra("name", student.name)
            intent.putExtra("roll", student.roll_no)
            intent.putExtra("age", student.age)
            intent.putExtra("course", student.course)

            startActivity(intent)
        }


    }
}