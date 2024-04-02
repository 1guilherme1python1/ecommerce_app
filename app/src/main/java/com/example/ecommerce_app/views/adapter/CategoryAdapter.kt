package com.example.ecommerce_app.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.ecommerce_app.R
import com.example.ecommerce_app.model.Category

class CategoryAdapter(
    private val lista: List<Category>
) : Adapter<CategoryAdapter.CategoryViewHolder>() {
    inner class CategoryViewHolder(
        itemView: View
    ): ViewHolder(itemView){

        var textTitleImgCat: TextView = itemView.findViewById(R.id.textTitleImgCat)
        var imgCat: ImageView = itemView.findViewById(R.id.img_cat)

        fun bind(item : Category){
            textTitleImgCat.text = item.title

            Glide
                .with(itemView)
                .load(item.picUrl)
                .into(imgCat)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(
            R.layout.item_list_category,
            parent,
            false
        )

        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val item = lista[position]

        holder.bind(item)
    }
}