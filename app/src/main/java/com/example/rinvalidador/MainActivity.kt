package com.example.rinvalidador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun Button_Click(view: View) {

        val intento1 = Intent(this, ListActivity::class.java)
        startActivity(intento1)

    }
}