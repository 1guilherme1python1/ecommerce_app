package com.example.ecommerce_app.views.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerce_app.R
import com.example.ecommerce_app.model.Banner

class SliderAdapter(
    private val lista: List<Banner>
) : Adapter<SliderAdapter.SliderViewHolder>() {

    inner class SliderViewHolder(
        val itemView: View
    ): ViewHolder(itemView){

        var imgSlider: ImageView = itemView.findViewById(R.id.img_item_slide)

        fun bind(item: Banner){

            var requestOptions = RequestOptions()
            requestOptions = requestOptions.transform(CenterCrop())

            Glide.with(itemView)
                .load(item.url)
                .apply(requestOptions)
                .into(imgSlider)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(
            R.layout.slide_item_container,
            parent,
            false
        )

        return SliderViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        val item = lista[position]

        holder.bind(item)
    }

}