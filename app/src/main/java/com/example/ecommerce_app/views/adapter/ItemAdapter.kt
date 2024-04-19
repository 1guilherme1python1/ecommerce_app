package com.example.ecommerce_app.views.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerce_app.R
import com.example.ecommerce_app.model.Item
import com.example.ecommerce_app.views.DetailActivity

class ItemAdapter(
    private val lista: List<Item>
) : Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(
        itemView: View
    ) : ViewHolder(itemView) {

        val imageProduct: ImageView = itemView.findViewById(R.id.image_product_pop)
        val ratingProduct: RatingBar = itemView.findViewById(R.id.ratingBarDetail)
        val titleProduct: TextView = itemView.findViewById(R.id.text_title_product_pop)
        val textOldPrice: TextView = itemView.findViewById(R.id.text_old_price_product_pop)
        val textNewPrice: TextView = itemView.findViewById(R.id.text_new_price_product_pop)
        val numbersReview: TextView = itemView.findViewById(R.id.text_numbers_review)
        val textRating: TextView = itemView.findViewById(R.id.text_rating_product)
        val itemProduct: ConstraintLayout = itemView.findViewById(R.id.item_list_product_list)

        fun bind(item: Item) {

            titleProduct.text = item.title

            textNewPrice.text = String.format("R$ %.2f", item.price.toFloat())
            numbersReview.text = item.review.toString()
            ratingProduct.rating = item.rating.toFloat()
            textRating.text = item.rating.toString()
            textOldPrice.text = textOldPrice.text


            val requestOptions = RequestOptions()
            requestOptions.transform(CenterCrop())

            Glide
                .with(itemView)
                .load(item.picUrl[0])
                .apply(requestOptions)
                .into(imageProduct)


            itemProduct.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra("objectItem", item)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(
            R.layout.item_list_products_pop,
            parent,
            false
        )

        return ItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = lista[position]

        holder.bind(item)
    }

}