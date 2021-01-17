package com.deepwares.fishmarketplaceconsumer.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deepwares.fishmarketplace.ui.home.OrderAdapter
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.ui.info.FishFragment
import kotlinx.android.synthetic.main.fragment_listings.*

class ListingsFragment : Fragment() {


    private lateinit var listingsViewModel: ListingsViewModel
    private lateinit var adapter: OrderAdapter
    var filter: Int = -1

    companion object {
        const val FILTER = "filter"
        fun newInstance() = ListingsFragment()
        fun newInstance(filter: Int) =
            ListingsFragment().apply { arguments = Bundle().apply { putInt(FILTER, filter) } }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.apply {
            filter = getInt(FILTER, -1)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listingsViewModel =
            ViewModelProvider(requireActivity()).get(ListingsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_listings, container, false)

        root.postDelayed({ list?.visibility = View.VISIBLE }, 3000)
        listingsViewModel.items.observe(viewLifecycleOwner, Observer {
            swipe_refresh.isRefreshing = false

            adapter.items.clear()
            if (filter == -1) {
                adapter.items.addAll(it)
            } else {
                it?.forEach {
                    if (it.species == filter) {
                        adapter.items.add(it)
                    }
                }
            }

            adapter.notifyDataSetChanged()
        })
        adapter = OrderAdapter()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = adapter
        empty_listing_text.setText(if (filter == -1) R.string.no_vendors_all else R.string.no_vendors)
        adapter.registerAdapterDataObserver(object : RecyclerView.AdapterDataObserver() {
            override fun onChanged() {
                updateEmptyState()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
                updateEmptyState()
            }

            override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
                updateEmptyState()
            }

            override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                updateEmptyState()
            }

            override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
                updateEmptyState()
            }

            override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                updateEmptyState()
            }


        })

        swipe_refresh.setOnRefreshListener { listingsViewModel.fetch() }
    }

    fun updateEmptyState() {
        val empty = adapter.itemCount == 0
        list.visibility = if (empty) View.GONE else View.VISIBLE
        empty_listing_text.visibility = if (empty) View.VISIBLE else View.GONE

    }

    override fun onResume() {
        super.onResume()
        listingsViewModel.fetch()
    }


}