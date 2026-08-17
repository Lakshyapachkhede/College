package com.pachkhede.assignment1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var etName: EditText
    private lateinit var etEmail: EditText
    private lateinit var etAddress: EditText
    private lateinit var etMobile: EditText
    private lateinit var etStudentId: EditText
    private lateinit var etCourse: EditText
    private lateinit var etDuration: EditText

    private lateinit var tvFee: TextView
    private lateinit var tvResult: TextView

    private val feePerMonth = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etName = findViewById(R.id.etName)
        etEmail = findViewById(R.id.etEmail)
        etAddress = findViewById(R.id.etAddress)
        etMobile = findViewById(R.id.etMobile)
        etStudentId = findViewById(R.id.etStudentId)
        etCourse = findViewById(R.id.etCourse)
        etDuration = findViewById(R.id.etDuration)

        tvFee = findViewById(R.id.tvFee)
        tvResult = findViewById(R.id.tvResult)

        findViewById<Button>(R.id.btnCalculate).setOnClickListener {
            calculateFee()
        }

        findViewById<Button>(R.id.btnRegister).setOnClickListener {
            register()
        }

        findViewById<Button>(R.id.btnReset).setOnClickListener {
            reset()
        }
    }

    private fun calculateFee() {
        val duration = etDuration.text.toString().toIntOrNull()

        if (duration == null || duration <= 0 || duration > 60) {
            etDuration.error = "Enter valid duration"
            return
        }

        val fee = duration * feePerMonth
        tvFee.text = "Fee: ₹$fee"
    }

    private fun register() {

        if (!validate()) return

        val name = etName.text.toString().trim()
        val email = etEmail.text.toString().trim()
        val address = etAddress.text.toString().trim()
        val mobile = etMobile.text.toString().trim()
        val studentId = etStudentId.text.toString().trim()
        val course = etCourse.text.toString().trim()
        val duration = etDuration.text.toString().trim()

        val fee = duration.toInt() * feePerMonth

        tvFee.text = "Fee: ₹$fee"

        tvResult.text = """
            Registration Summary
            
            Name: $name
            Email: $email
            Address: $address
            Mobile: $mobile
            Student ID: $studentId
            Course: $course
            Duration: $duration years
            Fee: ₹$fee
        """.trimIndent()
    }

    private fun validate(): Boolean {

        if (etName.text.toString().trim().isEmpty()) {
            etName.error = "Enter name"
            return false
        }

        val email = etEmail.text.toString().trim()

        if (email.isEmpty()) {
            etEmail.error = "Enter email"
            return false
        }

        if (!email.matches(Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))) {
            etEmail.error = "Invalid email"
            return false
        }

        if (etAddress.text.toString().trim().isEmpty()) {
            etAddress.error = "Enter address"
            return false
        }

        val mobile = etMobile.text.toString().trim()

        if (!mobile.matches(Regex("^[0-9]{10}$"))) {
            etMobile.error = "Enter 10-digit mobile number"
            return false
        }

        if (etStudentId.text.toString().trim().isEmpty()) {
            etStudentId.error = "Enter student ID"
            return false
        }

        if (etCourse.text.toString().trim().isEmpty()) {
            etCourse.error = "Enter course name"
            return false
        }

        val duration = etDuration.text.toString().toIntOrNull()

        if (duration == null || duration <= 0) {
            etDuration.error = "Enter valid duration"
            return false
        }

        return true
    }

    private fun reset() {

        etName.text.clear()
        etEmail.text.clear()
        etAddress.text.clear()
        etMobile.text.clear()
        etStudentId.text.clear()
        etCourse.text.clear()
        etDuration.text.clear()

        tvFee.text = "Fee: ₹0"
        tvResult.text = ""

        Toast.makeText(this, "Form Reset", Toast.LENGTH_SHORT).show()
    }
}