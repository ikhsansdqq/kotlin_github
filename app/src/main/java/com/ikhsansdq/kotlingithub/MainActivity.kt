package com.ikhsansdq.kotlingithub

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset


class MainActivity : AppCompatActivity() {
    lateinit var drawerLayout: DrawerLayout
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    var ninja_name: ArrayList<String> = ArrayList()
    var ninja_status: ArrayList<String> = ArrayList()
    var ninja_rank: ArrayList<String> = ArrayList()
    var ninja_image: ArrayList<String> = ArrayList()

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("ninja_list.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val myToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        myToolbar.title = ""
        setSupportActionBar(myToolbar)

        // drawer layout instance to toggle the menu icon to open
        // drawer and back button to close drawer
        drawerLayout = findViewById(R.id.my_drawer_layout)
        actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawerLayout, R.string.nav_open, R.string.nav_close)

        // pass the Open and Close toggle for the drawer layout listener
        // to toggle the button
        drawerLayout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()

        // to make the Navigation drawer icon always appear on the action bar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

//        RECYCLERVIEW
        val recyclerView = findViewById<RecyclerView>(R.id.reyclerView)
        val linearLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView.layoutManager = linearLayoutManager
        try {
            val obj = JSONObject(loadJSONFromAsset())
            val userArray = obj.getJSONArray("konoha")
            for (i in 0 until userArray.length()) {
                val userDetail = userArray.getJSONObject(i)
                ninja_name.add(userDetail.getString("name"))
                ninja_status.add(userDetail.getString("status"))
                ninja_rank.add(userDetail.getString("rank"))
                ninja_image.add(userDetail.getString("img_url"))
            }
        }
        catch (e: JSONException) {
            e.printStackTrace()
        }
        val ninjaAdapter = NinjaAdapter(this@MainActivity, ninja_name, ninja_status, ninja_rank, ninja_image)
        recyclerView.adapter = ninjaAdapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            true
        } else super.onOptionsItemSelected(item)
    }
}