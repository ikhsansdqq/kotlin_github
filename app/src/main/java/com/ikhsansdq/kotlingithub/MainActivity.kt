package com.ikhsansdq.kotlingithub

import android.content.res.AssetManager
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle
    private lateinit var dataList: ArrayList<String>
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        myToolbar.title = ""
        setSupportActionBar(myToolbar)

        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        drawerLayout.addDrawerListener(actionBarDrawerToggle); actionBarDrawerToggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        listView = findViewById<ListView>(R.id.listView)
        dataList = ArrayList()
        listView.setHeaderDividersEnabled(false); listView.setFooterDividersEnabled(false)

        parseJsonData()
        setupListView()
    }

    private fun setupListView() {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, dataList)
        listView.adapter = adapter
    }

    private fun parseJsonData() {
        val assetManager: AssetManager = assets
        try {
            val inputStream = assetManager.open("all_clans.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer); inputStream.close()
            val json = String(buffer, StandardCharsets.UTF_8)

            val jsonObject = JSONObject(json)
            val clansArray = jsonObject.getJSONArray("clans")
            for (i in 0 until clansArray.length()) {
                val clanObject = clansArray.getJSONObject(i)
                val name = clanObject.getString("name")
                dataList.add(name)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
