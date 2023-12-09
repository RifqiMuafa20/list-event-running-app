package com.pemrogmobile.kalenderlari

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListEventAdapter(private val listEvent: ArrayList<Event>) : RecyclerView.Adapter<ListEventAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_event, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, description, dateEvent, _, photo) = listEvent[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvEvent.text = dateEvent
        holder.tvDescription.text = description
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listEvent[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int = listEvent.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
        val tvName: TextView = itemView.findViewById(R.id.tv_item_name)
        val tvDescription: TextView = itemView.findViewById(R.id.tv_item_description)
        val tvEvent: TextView = itemView.findViewById(R.id.tv_item_date)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Event)
    }
}