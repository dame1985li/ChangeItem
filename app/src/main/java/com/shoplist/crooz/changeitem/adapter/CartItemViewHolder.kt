package com.shoplist.crooz.changeitem.adapter

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.shoplist.crooz.changeitem.R

class CartItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val nameView: TextView = itemView.findViewById(R.id.product_name)
    val addBuyLater: Button = itemView.findViewById(R.id.add_buy_later)
}