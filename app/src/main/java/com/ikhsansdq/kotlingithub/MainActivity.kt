package com.ikhsansdq.kotlingithub

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.ikhsansdq.kotlingithub.adapter.GVMainActivityAdapter
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    private lateinit var gridView: GridView
    private lateinit var adapter: GVMainActivityAdapter

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

        gridView = findViewById(R.id.mainActivityGridView)
        val itemClickListener = object : GVMainActivityAdapter.OnItemClickListener {
            override fun onItemClick(item: String) {
                val intent = Intent(this@MainActivity, ClickedItemActivity::class.java)
                println("$item is clicked and will go to $applicationContext")
                intent.putExtra("selectedItem", item)
                startActivity(intent)
            }
        }
        adapter = GVMainActivityAdapter(this, itemClickListener)
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
            gridView.adapter = adapter
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

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val selectedItem = adapter.getItem(position) as String
        try {
            val intent = Intent(this, ClickedItemActivity::class.java)
            intent.putExtra("selectedItem", selectedItem)
            startActivity(intent)
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

}
