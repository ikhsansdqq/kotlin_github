package com.ikhsansdq.kotlingithub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ikhsansdq.kotlingithub.R

class GVMainActivityAdapter(private val context: Context, private val listener: OnItemClickListener) : BaseAdapter() {
    private val clanNames = mutableListOf<String>()
    private val inflater: LayoutInflater = LayoutInflater.from(context)

    fun setClanNames(names: List<String>) {
        clanNames.clear()
        clanNames.addAll(names)
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return clanNames.size
    }

    override fun getItem(position: Int): Any {
        return clanNames[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View =
            convertView ?: inflater.inflate(R.layout.main_card_recyclerview, parent, false)

        val nameTextView: TextView = view.findViewById(R.id.card_title)
        val name = getItem(position) as String
        nameTextView.text = name

        view.setOnClickListener{
            listener.onItemClick(name)
        }

        return view
    }

    interface OnItemClickListener {
        fun onItemClick(item: String)
    }

}