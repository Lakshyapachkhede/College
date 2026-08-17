package com.pachkhede.assignment2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import android.content.Intent
import android.net.Uri
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {


    private lateinit var etService: android.widget.EditText
    private lateinit var etPhone: android.widget.EditText
    private lateinit var etEmail: android.widget.EditText
    private lateinit var etAddress: android.widget.EditText
    private lateinit var etUrl: android.widget.EditText
    private lateinit var etMessage: android.widget.EditText

    private lateinit var btnDial: MaterialButton
    private lateinit var btnEmail: MaterialButton
    private lateinit var btnWebsite: MaterialButton
    private lateinit var btnLocation: MaterialButton
    private lateinit var btnSms: MaterialButton
    private lateinit var btnShare: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        etService = findViewById(R.id.etService)
        etPhone = findViewById(R.id.etPhone)
        etEmail = findViewById(R.id.etEmail)
        etAddress = findViewById(R.id.etAddress)
        etUrl = findViewById(R.id.etUrl)
        etMessage = findViewById(R.id.etMessage)

        btnDial = findViewById(R.id.btnDial)
        btnEmail = findViewById(R.id.btnEmail)
        btnWebsite = findViewById(R.id.btnWebsite)
        btnLocation = findViewById(R.id.btnLocation)
        btnSms = findViewById(R.id.btnSms)
        btnShare = findViewById(R.id.btnShare)




        btnDial.setOnClickListener {

            val phone = etPhone.text.toString().trim()

            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$phone"))

            startActivity(intent)
        }


        btnEmail.setOnClickListener {

            val email = etEmail.text.toString().trim()
            val message = etMessage.text.toString().trim()

            val intent = Intent(Intent.ACTION_SENDTO,Uri.parse("mailto:$email"))
            intent.putExtra(Intent.EXTRA_TEXT, message)

            startActivity(intent)
        }




        btnWebsite.setOnClickListener {
            val url = etUrl.text.toString().trim()
            val intent = Intent(Intent.ACTION_VIEW,Uri.parse("https://$url"))

            startActivity(intent)
        }




        btnLocation.setOnClickListener {

            val address = etAddress.text.toString().trim()

            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q=${Uri.encode(address)}"))

            startActivity(intent)
        }




        btnSms.setOnClickListener {

            val phone = etPhone.text.toString().trim()
            val message = etMessage.text.toString().trim()

            val intent = Intent(Intent.ACTION_SENDTO,Uri.parse("sms:$phone"))
            intent.putExtra("sms_body", message)

            startActivity(intent)
        }




        btnShare.setOnClickListener {


            val service = etService.text.toString().trim()
            val phone = etPhone.text.toString().trim()
            val email = etEmail.text.toString().trim()
            val address = etAddress.text.toString().trim()
            val message = etMessage.text.toString().trim()


            val information = """
                Share
              
                Service: $service
                Phone: $phone
                Message: $message
                Email: $email
                Address: $address
            """.trimIndent()

            val intent = Intent(Intent.ACTION_SEND)

            intent.type = "text/plain"
            intent.putExtra(Intent.EXTRA_TEXT, information)

            startActivity(
                Intent.createChooser(intent, "Share Information")
            )
        }
    }
}