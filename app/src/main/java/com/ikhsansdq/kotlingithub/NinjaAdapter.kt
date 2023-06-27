package com.ikhsansdq.kotlingithub

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.squareup.picasso.Picasso

class NinjaAdapter(
    private var context: Context,
    private var ninjaNames: ArrayList<String>,
    private var ninjaStatus: ArrayList<String>,
    private var ninjaRank: ArrayList<String>,
    private var ninjaImg: ArrayList<String>
) : RecyclerView.Adapter<NinjaAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NinjaAdapter.MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_ninja, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: NinjaAdapter.MyViewHolder, position: Int) {
        holder.ninja_name.text = ninjaNames[position]
        holder.ninja_status.text = ninjaStatus[position]
        holder.ninja_rank.text = ninjaRank[position]

        Picasso.get()
            .load(ninjaImg[position])
            .into(holder.ninja_img)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, ninjaNames[position], Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return ninjaNames.size
    }

    inner class MyViewHolder(itemView: View) : ViewHolder(itemView) {
        var ninja_name: TextView = itemView.findViewById<View>(R.id.ninja_name_placeholder) as TextView
        var ninja_status: TextView = itemView.findViewById<View>(R.id.ninja_status_placeholder) as TextView
        var ninja_rank: TextView = itemView.findViewById<View>(R.id.ninja_rank_placeholder) as TextView
        var ninja_img: ImageView = itemView.findViewById<View>(R.id.ninja_img_placeholder) as ImageView
    }
}