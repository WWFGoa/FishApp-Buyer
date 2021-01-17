package com.deepwares.fishmarketplaceconsumer.ui.orders

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepwares.fishmarketplace.ui.home.MyOrderAdapter
import com.deepwares.fishmarketplace.ui.home.OrderAdapter
import com.deepwares.fishmarketplaceconsumer.R
import kotlinx.android.synthetic.main.fragment_orders.*

class OrdersFragment : Fragment() {

    private lateinit var ordersViewModel: OrdersViewModel
    private lateinit var adapter: MyOrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        ordersViewModel =
            ViewModelProvider(this).get(OrdersViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_orders, container, false)
        ordersViewModel.text.observe(viewLifecycleOwner, Observer {
        })
        adapter = MyOrderAdapter(this)
        ordersViewModel.items.observe(viewLifecycleOwner, Observer {
            swipe_refresh.isRefreshing = false
            adapter.items.clear()
            adapter.items.addAll(it)
            adapter.notifyDataSetChanged()
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
        swipe_refresh.setOnRefreshListener {
            ordersViewModel.fetch()
        }
    }

    override fun onResume() {
        super.onResume()
        ordersViewModel.fetch()
    }


    fun launchMaps(address: String) {
        val gmmIntentUri = Uri.parse("geo:0,0?q=${address}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
        mapIntent.resolveActivity(requireContext()!!.packageManager)?.let {
            startActivity(mapIntent)
        }
    }


    fun contact(contact: String) {
        val contactUri = Uri.parse("tel:${contact}")
        val contactIntent = Intent(Intent.ACTION_DIAL, contactUri)
        contactIntent.setData(contactUri)
        contactIntent.resolveActivity(requireContext()!!.packageManager)?.let {
            startActivity(contactIntent)
        }
    }

    override fun onDestroy() {
        adapter?.fragment = null
        super.onDestroy()
    }
}