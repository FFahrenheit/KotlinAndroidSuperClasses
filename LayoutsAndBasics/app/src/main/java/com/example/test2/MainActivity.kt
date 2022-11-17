package com.example.test2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var clickMeButton : Button
    private lateinit var clickMeButton2 : Button
    private lateinit var submitButton : Button
    private lateinit var hellotext : TextView
    private lateinit var inputText : EditText

    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickMeButton = findViewById(R.id.clickButton)
        clickMeButton2 = findViewById(R.id.clickButton2)
        hellotext = findViewById(R.id.helloText)
        inputText = findViewById(R.id.inputText)
        submitButton = findViewById(R.id.submitButton)

        val currentText = hellotext.text
        Log.d("Texto", currentText.toString())

        hellotext.text = "Hola"

        clickMeButton.setOnClickListener(){
            clicked()
        }

        clickMeButton2.setOnClickListener(){
            makeSnackbar()
        }

        submitButton.setOnClickListener(){
            onSubmit()
        }
    }

    private fun onSubmit(){
        val texto = inputText.text.toString()
        if(texto == ""){
            inputText.error = "Ingrese un nombre"
            return;
        }
        // Moverse a la otra actividad
        val myIntent = Intent(this, OtherActivity::class.java)
        myIntent.putExtra("name", texto)
        startActivity(myIntent)
    }

    private fun clicked(){
        Toast.makeText(this, "Me hiciste click", Toast.LENGTH_LONG).show()
        counter++
        hellotext.text = "Hola ${counter} mundo"
    }

    private fun makeSnackbar(){
        Snackbar.make(clickMeButton2, "Me hiciste click", Snackbar.LENGTH_LONG)
            .setAction("Undo", View.OnClickListener {
                clicked()
            })
            .setActionTextColor(resources.getColor(R.color.white))
            .show()
    }

}