package com.pachkhede.practiceexercise1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var  image : ImageView
    private lateinit var  btn : Button
    private lateinit var  result : TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image = findViewById(R.id.image)
        btn = findViewById(R.id.btn)
        result = findViewById(R.id.result)

        btn.setOnClickListener {

            btn.isEnabled = false
            val random = listOf(true, false).random()

            image.animate()
                .rotationXBy(1800f)
                .setDuration(1500)
                .withEndAction {

                    if(random)
                    {
                        image.setImageResource(R.drawable.head)
                        result.text = "Result: HEAD"
                    }
                    else
                    {
                        image.setImageResource(R.drawable.tail)
                        result.text = "Result: TAIL"


                    }

                    image.rotationX = 0f

                    btn.isEnabled = true

                }
                .start()



        }


    }
}