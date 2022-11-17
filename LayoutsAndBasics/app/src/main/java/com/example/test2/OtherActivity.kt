package com.example.test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class OtherActivity : AppCompatActivity() {
    private lateinit var helloText : TextView
    private lateinit var imageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_other)

        helloText = findViewById(R.id.helloText)
        imageView = findViewById(R.id.imageView)

        val extra = intent.getStringExtra("name")
        helloText.text = "Hola, ${extra}"

        val cutePuppy = "https://paradepets.com/.image/ar_1:1%2Cc_fill%2Ccs_srgb%2Cfl_progressive%2Cq_auto:good%2Cw_1200/MTkxMzY1Nzg4NjczMzIwNTQ2/cutest-dog-breeds-jpg.jpg"
        Picasso.with(this).load(cutePuppy).into(imageView)
    }
}
