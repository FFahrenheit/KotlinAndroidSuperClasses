package com.example.secondapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.secondapp.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        actionBar?.title = "com.example.secondapp.data.Dog App"
        supportActionBar?.title = "com.example.secondapp.data.Dog App"


    }
}