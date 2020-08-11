package com.deepwares.fishmarketplaceconsumer.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.deepwares.fishmarketplace.ui.home.OrderAdapter
import com.deepwares.fishmarketplaceconsumer.R
import kotlinx.android.synthetic.main.fragment_listings.*

class ListingsFragment : Fragment() {


    private lateinit var listingsViewModel: ListingsViewModel
    private lateinit var adapter: OrderAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listingsViewModel =
            ViewModelProvider(this).get(ListingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_listings, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        listingsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })

        root.postDelayed({ list?.visibility = View.VISIBLE }, 3000)
        listingsViewModel.items.observe(viewLifecycleOwner, Observer {
            adapter.items.clear()
            adapter.items.addAll(it)
            adapter.notifyDataSetChanged()
        })
        adapter = OrderAdapter()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        listingsViewModel.fetch()
    }


}