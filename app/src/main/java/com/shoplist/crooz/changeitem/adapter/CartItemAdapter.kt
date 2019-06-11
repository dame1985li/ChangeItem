package com.shoplist.crooz.changeitem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoplist.crooz.changeitem.R
import com.shoplist.crooz.changeitem.viewmodel.CartViewModel

class CartItemAdapter(private val list: List<CartViewModel>, private  val listener: ListListener) :
    RecyclerView.Adapter<CartItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartItemViewHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return CartItemViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: CartItemViewHolder, position: Int) {
        holder.nameView.text = list[position].itemName
        holder.addBuyLater.text = "後で買う"
        holder.addBuyLater.setOnClickListener {
            listener.onClickRow(it, list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ListListener {
        fun onClickRow(tappedView: View, cartViewModel: CartViewModel)
    }
}