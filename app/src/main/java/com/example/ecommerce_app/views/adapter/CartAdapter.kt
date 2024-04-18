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
import com.example.ecommerce_app.model.Item

class CartAdapter(
    private val listCard: List<Item>
) : Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(
        itemView: View
    ) : ViewHolder(itemView) {

        private val imgProductCart: ImageView = itemView.findViewById(R.id.imgItemCart)
        private val titleProductCart: TextView = itemView.findViewById(R.id.textTitleItemCart)
        private val textProductPrice: TextView = itemView.findViewById(R.id.textPriceItemCart)
        private val textProductTotalPrice: TextView = itemView.findViewById(R.id.textTotalPriceItemCart)
        private val textTotalItemsProductCart: TextView = itemView.findViewById(R.id.textTotalItemsProductCart)
        private val btnSubtractionProductCart: TextView = itemView.findViewById(R.id.btnSubtractProductCart)
        private val btnSumProductCart: TextView = itemView.findViewById(R.id.btnSumProductCart)

        fun bind(item: Item, position: Int) {

            var totalItemsCard = obterQuatidadeProduto(item)

            titleProductCart.text = item.title
            textProductPrice.text = String.format("R$ %.2f", item.price.toFloat())
            textProductTotalPrice.text =  String.format("R$ %.2f", item.price.toFloat())
            textTotalItemsProductCart.text = totalItemsCard.toString()

            btnSubtractionProductCart.setOnClickListener {
                if(totalItemsCard > 0){
                    totalItemsCard-=1
                    notifyItemChanged(position)
                }
            }

            btnSumProductCart.setOnClickListener {
                totalItemsCard+=1
                notifyItemChanged(position)
            }

            Glide
                .with(itemView)
                .load(item.picUrl[0])
                .into(imgProductCart)
        }
    }

    fun obterQuatidadeProduto(item: Item): Int{
        return listCard.count{ it.title == item.title }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CartAdapter.CartViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val itemView = layoutInflater.inflate(
            R.layout.item_list_cart,
            parent,
            false
        )

        return CartViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return listCard.size
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val item = listCard[position]

        holder.bind(item, position)
    }
}