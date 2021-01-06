package com.deepwares.fishmarketplaceconsumer.ui.listing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.deepwares.fishmarketplaceconsumer.BaseDialogFragment
import com.deepwares.fishmarketplaceconsumer.R
import kotlinx.android.synthetic.main.fragment_filtered_listings.*

class FilteredListingFragment : BaseDialogFragment() {
    val args: FilteredListingFragmentArgs by navArgs()

    companion object {
        const val FILTER = "filter"
        fun newInstance() = FilteredListingFragment()
        fun newInstance(filter: Int) =
            FilteredListingFragment().apply {
                arguments = Bundle().apply { putInt(FILTER, filter) }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_filtered_listings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragment = ListingsFragment.newInstance(args.filter)
        toolbar.title = args.title + " - Sellers"
        childFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        toolbar.setNavigationOnClickListener {
            dismiss()
        }
    }


}