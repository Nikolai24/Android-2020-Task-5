package com.example.catretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.catretrofit.R
import com.example.catretrofit.data.Cat

class DataAdapter(items: MutableList<Cat>, listener: OnItemClickListener) : RecyclerView.Adapter<DataAdapter.ViewHolder>() {
    private val items: MutableList<Cat>
    private val itemsAll: MutableList<Cat>
    private val listener: OnItemClickListener

    init {
        this.items = ArrayList(items)
        this.itemsAll = ArrayList(items)
        this.listener = listener
    }

    fun setItems(itemsNew: List<Cat>) {
        items.clear()
        itemsAll.clear()
        items.addAll(itemsNew)
        itemsAll.addAll(itemsNew)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(item: Cat, position: Int)
    }

    class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cat, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Cat = items[position]
        holder.imageView.load(item.imageUrl)
        holder.itemView.setOnClickListener { listener.onItemClick(item, position) }
    }

    override fun getItemCount() = items.size
}