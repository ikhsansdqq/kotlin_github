package com.ikhsansdq.kotlingithub.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ikhsansdq.kotlingithub.R

class RVMainActivityAdapter : RecyclerView.Adapter<RVMainActivityAdapter.ViewHolder>() {
    private val clanNames = mutableListOf<String>()

    fun setClanNames(names: List<String>) {
        clanNames.clear()
        clanNames.addAll(names)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_card_recyclerview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = clanNames[position]
        holder.clanTextView.text = name
    }

    override fun getItemCount(): Int {
        return clanNames.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clanTextView: TextView = itemView.findViewById(R.id.card_clan_title)
    }

}