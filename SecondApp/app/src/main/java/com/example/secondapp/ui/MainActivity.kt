package com.example.secondapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.secondapp.R
import com.example.secondapp.data.DogViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var dogsViewModel : DogViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dogsViewModel = ViewModelProvider(this)[DogViewModel::class.java]
        dogsViewModel.getItem().observe(this) {
            // Do something
            Log.d("ViewModelCount", it.size.toString())
        }

        actionBar?.title = "Dog App"
        supportActionBar?.title = "Dog App"
    }
}