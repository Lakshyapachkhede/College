package com.pachkhede.practiceexercise4

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity2 : AppCompatActivity() {

    private lateinit var name: TextView
    private lateinit var roll: TextView
    private lateinit var age: TextView
    private lateinit var course: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        name = findViewById(R.id.name)
        roll = findViewById(R.id.roll)
        age = findViewById(R.id.age)
        course = findViewById(R.id.course)

        val studentName = intent.getStringExtra("name")
        val studentRoll = intent.getIntExtra("roll", 0)
        val studentAge = intent.getIntExtra("age", 0)
        val studentCourse = intent.getStringExtra("course")

        name.text = "Name: $studentName"
        roll.text = "Roll No: $studentRoll"
        age.text = "Age: $studentAge"
        course.text = "Course: $studentCourse"
    }
}