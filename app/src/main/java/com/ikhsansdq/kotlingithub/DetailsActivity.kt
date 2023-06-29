package com.ikhsansdq.kotlingithub

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val selectedItem = intent.getStringExtra("selectedItem")
        val textView: TextView = findViewById(R.id.myTextView)
        textView.text = selectedItem

        val detailsToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.detailsToolbar)
        detailsToolbar.title = selectedItem
        setSupportActionBar(detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailsToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}