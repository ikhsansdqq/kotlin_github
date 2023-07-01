package com.ikhsansdq.kotlingithub

import android.content.res.AssetManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ikhsansdq.kotlingithub.adapter.RVCharactersAdapter
import org.json.JSONObject
import java.util.Locale

class ClickedItemActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: RVCharactersAdapter

    private lateinit var searchView: SearchView

    private lateinit var originalList: List<String>
    private lateinit var filteredList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.clicked_item_activity)

        val selectedItem = intent.getStringExtra("selectedItem")

        val fileName = when (selectedItem) {
            "Villages" -> "villages.json"
            "Characters" -> "character.json"
            "Clans" -> "clans.json"
            "Kara" -> "kara.json"
            "Kekkeigenkai" -> "kekkei_genkai.json"
            "Tailed Beasts" -> "tailed_beast.json"
            "Teams" -> "team.json"
            "Akatsuki" -> "akatsuki.json"
            else -> throw IllegalArgumentException("Invalid item selected")
        }

        val jsonString = loadJsonDataFromAssets(fileName)
        val jsonData = JSONObject(jsonString)

        val listDariArray = mutableListOf<String>()

        if (jsonData.has("villages")) {
            val villagesArray = jsonData.getJSONArray("villages")

            for (i in 0 until villagesArray.length()) {
                val villageObject = villagesArray.getJSONObject(i)
                val villagesObject = villageObject.getString("name")
                listDariArray.add(villagesObject)
            }
        } else if (jsonData.has("characters")) {
            val charactersArray = jsonData.getJSONArray("characters")

            for (i in 0 until charactersArray.length()) {
                val characterObject = charactersArray.getJSONObject(i)
                val charactersObject = characterObject.getString("name")
                listDariArray.add(charactersObject)
            }
        } else if (jsonData.has("clans")) {
            val clansArray = jsonData.getJSONArray("clans")

            for (i in 0 until clansArray.length()) {
                val clanObject = clansArray.getJSONObject(i)
                val clansObject = clanObject.getString("name")
                listDariArray.add(clansObject)
            }
        } else if (jsonData.has("kara")) {
            val clansArray = jsonData.getJSONArray("kara")

            for (i in 0 until clansArray.length()) {
                val clanObject = clansArray.getJSONObject(i)
                val clansObject = clanObject.getString("name")
                listDariArray.add(clansObject)
            }
        } else if (jsonData.has("kekkeigenkai")) {
            val clansArray = jsonData.getJSONArray("kekkeigenkai")

            for (i in 0 until clansArray.length()) {
                val clanObject = clansArray.getJSONObject(i)
                val clansObject = clanObject.getString("name")
                listDariArray.add(clansObject)
            }
        } else if (jsonData.has("tailedBeasts")) {
            val clansArray = jsonData.getJSONArray("tailedBeasts")

            for (i in 0 until clansArray.length()) {
                val clanObject = clansArray.getJSONObject(i)
                val clansObject = clanObject.getString("name")
                listDariArray.add(clansObject)
            }
        } else if (jsonData.has("akatsuki")) {
            val clansArray = jsonData.getJSONArray("akatsuki")

            for (i in 0 until clansArray.length()) {
                val clanObject = clansArray.getJSONObject(i)
                val clansObject = clanObject.getString("name")
                listDariArray.add(clansObject)
            }
        } else if (jsonData.has("teams")) {
            val clansArray = jsonData.getJSONArray("teams")

            for (i in 0 until clansArray.length()) {
                val clanObject = clansArray.getJSONObject(i)
                val clansObject = clanObject.getString("name")
                listDariArray.add(clansObject)
            }
        }

        recyclerView = findViewById(R.id.itemRecyclerView)
        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        originalList = listDariArray
        filteredList = listDariArray.toMutableList()

        adapter = RVCharactersAdapter(this)
        adapter.setNames(listDariArray)
        recyclerView.adapter = adapter

        val detailsToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.detailsToolbar)
        detailsToolbar.title = selectedItem
        setSupportActionBar(detailsToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        detailsToolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        searchView = findViewById(R.id.searchView)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterData(newText)
                return true
            }
        })

        searchView.queryHint = "Search..."
    }

    private fun filterData(query: String) {
        filteredList.clear()
        if (query.isEmpty()) {
            filteredList.addAll(originalList)
        } else {
            val searchQuery = query.lowercase(Locale.getDefault())
            originalList.forEach { item ->
                if (item.lowercase(Locale.getDefault()).contains(searchQuery)) {
                    filteredList.add(item)
                }
            }
        }
        adapter.setNames(filteredList)
    }

    private fun loadJsonDataFromAssets(fileName: String): String {
        val assetManager: AssetManager = assets
        val inputStream = assetManager.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        return String(buffer, Charsets.UTF_8)
    }
}

