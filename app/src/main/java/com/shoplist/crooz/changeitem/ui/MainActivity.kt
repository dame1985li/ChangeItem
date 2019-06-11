package com.shoplist.crooz.changeitem.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.shoplist.crooz.changeitem.adapter.BuyLaterItemAdapter
import com.shoplist.crooz.changeitem.adapter.CartItemAdapter
import com.shoplist.crooz.changeitem.viewmodel.BuyLaterViewModel
import com.shoplist.crooz.changeitem.viewmodel.CartViewModel
import kotlinx.android.synthetic.main.buy_later_list_view.*
import kotlinx.android.synthetic.main.cart_list_view.*
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val dataList = mutableListOf<CartViewModel>()
    private val buyLaterList = mutableListOf<BuyLaterViewModel>()

    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerView.Adapter<*>? = null

    private var buylaterlistrecyclerView: RecyclerView? = null
    private var buyLaterAdapter: RecyclerView.Adapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.shoplist.crooz.changeitem.R.layout.activity_main)

        recyclerView = cart_list_recycler_view
        buylaterlistrecyclerView = buy_later_list_recycler_view

        adapter = CartItemAdapter(createDataList(), object : CartItemAdapter.ListListener {
            override fun onClickRow(tappedView: View, cartViewModel: CartViewModel) {
                this@MainActivity.onClickRow(tappedView, cartViewModel)
            }
        })
        buyLaterAdapter = BuyLaterItemAdapter(createList(), object : BuyLaterItemAdapter.ListListener {
            override fun onClickRow(tappedView: View, buyLaterViewModel: BuyLaterViewModel) {
                this@MainActivity.onClickRow(tappedView, buyLaterViewModel)
            }
        })

        recyclerView?.setHasFixedSize(true)
        recyclerView?.isNestedScrollingEnabled = false
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView?.adapter = adapter

        buylaterlistrecyclerView?.setHasFixedSize(true)
        buylaterlistrecyclerView?.isNestedScrollingEnabled = false
        buylaterlistrecyclerView?.layoutManager = LinearLayoutManager(this)
        buylaterlistrecyclerView?.adapter = buyLaterAdapter
    }

    private fun createList(): List<BuyLaterViewModel> {
        for (i in 0..1) {
            val data: BuyLaterViewModel = BuyLaterViewModel().also {
                it.itemBuyLaterName = "後で買う商品" + i
            }
            buyLaterList.add(data)
        }
        return buyLaterList
    }

    private fun createDataList(): List<CartViewModel> {
        for (i in 0..1) {
            val data: CartViewModel = CartViewModel().also {
                it.itemName = "商品" + i
            }
            dataList.add(data)
        }
        return dataList
    }

    fun onClickRow(tappedView: View, cartViewModel: CartViewModel) {
        Log.d("MainActivity","タップ" + cartViewModel.itemName)
        dataList.remove(cartViewModel)
        adapter?.notifyDataSetChanged()
        val data: BuyLaterViewModel = BuyLaterViewModel().also {
            it.itemBuyLaterName = cartViewModel.itemName
        }
        buyLaterList.add(data)
        buyLaterAdapter?.notifyDataSetChanged()
    }

    fun onClickRow(tappedView: View, buyLaterViewModel: BuyLaterViewModel) {
        Log.d("MainActivity","タップ" + buyLaterViewModel.itemBuyLaterName)
        buyLaterList.remove(buyLaterViewModel)
        buyLaterAdapter?.notifyDataSetChanged()
        val data: CartViewModel = CartViewModel().also {
            it.itemName = buyLaterViewModel.itemBuyLaterName
        }
        dataList.add(data)
        adapter?.notifyDataSetChanged()
    }
}

