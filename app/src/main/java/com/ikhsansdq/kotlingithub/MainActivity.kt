package com.ikhsansdq.kotlingithub

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikhsansdq.kotlingithub.adapter.RVMainActivityAdapter
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RVMainActivityAdapter

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

        recyclerView = findViewById(R.id.mainActivityRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = RVMainActivityAdapter()
        recyclerView.adapter = adapter
        parseJSON()
    }

    private fun parseJSON() {
        try {
            val inputStream: InputStream = assets.open("scheme.json")
            val size: Int = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()

            val jsonStringGetter = String(buffer, Charsets.UTF_8)
            val jsonObjectGetter = JSONObject(jsonStringGetter)
            val clansArray = jsonObjectGetter.getJSONArray("scheme")

            val clanNames = mutableListOf<String>()
            for (i in 0 until clansArray.length()) {
                val clanObject = clansArray.getJSONObject(i)
                val name = clanObject.getString("name")
                clanNames.add(name)
            }
            adapter.setClanNames(clanNames)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            println("Something has wrong with ${e.printStackTrace()}")
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}
