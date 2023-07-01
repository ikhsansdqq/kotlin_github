package com.ikhsansdq.kotlingithub.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.ikhsansdq.kotlingithub.R

class RVCharactersAdapter(private var context: Context) : RecyclerView.Adapter<RVCharactersAdapter.ViewHolder>() {
    private val characterNames = mutableListOf<String>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.main_card_recyclerview, parent, false)
        return ViewHolder(view)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        private val nameTextView: TextView = itemView.findViewById(R.id.card_title)

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val clickedItem = characterNames[position]
                Toast.makeText(context, "Clicked: $clickedItem", Toast.LENGTH_SHORT).show()
            }
        }
        fun bind(name: String) {
            nameTextView.text = name
        }

        init {
            itemView.setOnClickListener(this)
        }

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val name = characterNames[position]
        holder.bind(name)
    }

    override fun getItemCount(): Int {
        return characterNames.size
    }

    fun setNames(names: List<String>) {
        characterNames.clear()
        characterNames.addAll(names)
        notifyDataSetChanged()
    }
}
