package com.shoplist.crooz.changeitem.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.shoplist.crooz.changeitem.R
import com.shoplist.crooz.changeitem.viewmodel.BuyLaterViewModel

class BuyLaterItemAdapter (private val list: List<BuyLaterViewModel>, private val listener: ListListener) :
    RecyclerView.Adapter<BuyLaterItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuyLaterItemViewHolder {
        val rowView: View = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return BuyLaterItemViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: BuyLaterItemViewHolder, position: Int) {
        holder.nameView.text = list[position].itemBuyLaterName
        holder.addCart.text = "カートに戻す"
        holder.addCart.setOnClickListener {
            listener.onClickRow(it, list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    interface ListListener {
        fun onClickRow(tappedView: View, cartViewModel: BuyLaterViewModel)
    }
}