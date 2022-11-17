package com.example.firstapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {
    private lateinit var helloTextView : TextView
    private lateinit var helloButton : Button
    private lateinit var byeButton : Button
    private lateinit var imageView : ImageView
    private var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar?.title = "Dians"
        supportActionBar?.title = "Diana"

        this.helloTextView = findViewById(R.id.helloTextView)
        this.helloTextView.setText("Hola Diana!")

        this.imageView = findViewById(R.id.imageView)

        this.helloButton = findViewById(R.id.helloButton)
        this.helloButton.setOnClickListener(){ v : View ->
            this.count++
            this.helloTextView.text = "Clicked ${this.count} times..."
            Log.d("It", v.toString())
            Toast.makeText(this.applicationContext, "Button clicked!", Toast.LENGTH_LONG).show()
            if(this.count == 10){
                val intent = Intent(this, SecondActivity::class.java)
                startActivity(intent)
            }
        }


        this.byeButton = findViewById(R.id.byeButton)
        this.byeButton.setOnClickListener(){
            Snackbar.make(it, "Hello", Snackbar.LENGTH_LONG)
                    .setAction("Undo", View.OnClickListener {
                        this@MainActivity.count--
                        this@MainActivity.helloTextView.text = "Clicked ${this.count} times..."
                        var source : String = "https://upload.wikimedia.org/wikipedia/en/thumb/b/ba/Flag_of_Germany.svg/1200px-Flag_of_Germany.svg.png"
                        if(this.count == -5){
                            Picasso.with(this).load(source).into(this.imageView)
                        }
                    }).show()

        }

    }
}