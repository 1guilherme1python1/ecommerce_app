package com.example.ecommerce_app.views.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.ecommerce_app.R

class SizeAdapter(
    val lista: List<String>,
    var selectedPosition: Int = -1,
    var lastSelectedPosition: Int = -1
) : Adapter<SizeAdapter.SizeViewHolder>() {
    inner class SizeViewHolder(
        itemView: View
    ) : ViewHolder(itemView) {

        @SuppressLint("ResourceAsColor")
        fun bind(itemList: String) {

            val textSizeItem: TextView = itemView.findViewById(R.id.textSizeItem)
            val ctSizeLayout: ConstraintLayout = itemView.findViewById(R.id.itemSizeList)

            textSizeItem.text = itemList

            itemView.rootView.setOnClickListener {
                lastSelectedPosition = selectedPosition
                selectedPosition = adapterPosition
                notifyItemChanged(lastSelectedPosition)
                notifyItemChanged(selectedPosition)
            }

            if (selectedPosition == adapterPosition) {
                ctSizeLayout.setBackgroundResource(R.drawable.size_selected)
                textSizeItem.setTextColor(R.color.green)
            } else {
                ctSizeLayout.setBackgroundResource(R.drawable.size_unselected)
                textSizeItem.setTextColor(R.color.black)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(
            R.layout.item_list_size,
            parent,
            false
        )

        return SizeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: SizeViewHolder, position: Int) {
        val item = lista[position]

        holder.bind(item)
    }

}