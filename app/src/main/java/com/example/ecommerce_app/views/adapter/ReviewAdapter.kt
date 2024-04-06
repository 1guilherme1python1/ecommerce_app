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
import com.example.ecommerce_app.model.Review

class ReviewAdapter(
    val listaReview: List<Review>
) : Adapter<ReviewAdapter.ReviewViewHolder>() {
    inner class ReviewViewHolder(itemView: View): ViewHolder(itemView){

        private val imgPersonReview: ImageView = itemView.findViewById(R.id.imgPersonReview)
        private val namePersonReview: TextView  = itemView.findViewById(R.id.textNameReview)
        private val descriptionPersonReview: TextView  = itemView.findViewById(R.id.textDescriptionReview)
        private val ratingPersonReview: TextView  = itemView.findViewById(R.id.textRatingReview)

        fun bind(item: Review){
            namePersonReview.text = item.Name
            descriptionPersonReview.text = item.Description
            ratingPersonReview.text = item.rating.toString()

            Glide
                .with(itemView)
                .load(item.PicUrl)
                .into(imgPersonReview)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(
            R.layout.item_list_review,
            parent,
            false
        )

        return ReviewViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listaReview.size
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val item = listaReview[position]

        holder.bind(item)
    }
}