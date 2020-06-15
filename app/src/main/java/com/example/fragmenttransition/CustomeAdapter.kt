package com.example.fragmenttransition

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class CustomeAdapter () : ListAdapter<Int, ItemViewHolder>(DiffCallback()){

    lateinit var onClick : (Int) -> Unit

    fun setOnClickListener(onClick: (Int) -> Unit){
        this.onClick = onClick
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item, parent, false)
        val holder = ItemViewHolder(view)

        holder.imageView.setOnClickListener{
            onClick(getItem(holder.adapterPosition))
        }
        return holder
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d("Adapter", ""+ getItem(position))
        Glide.with(holder.imageView)
            .load(getItem(position))
            .into(holder.imageView)


    }

}

class ItemViewHolder(containerView : View): RecyclerView.ViewHolder(containerView){
    val imageView: ImageView = containerView.findViewById(R.id.image)
}

class DiffCallback : DiffUtil.ItemCallback<Int>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}