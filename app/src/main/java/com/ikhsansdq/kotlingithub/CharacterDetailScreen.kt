package com.ikhsansdq.kotlingithub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class CharacterDetailScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail_screen)

        val detailsToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.detailsToolbar)
        detailsToolbar.title = "Madara Uchiha"
        setSupportActionBar(detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailsToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

    }
}