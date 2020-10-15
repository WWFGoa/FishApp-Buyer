package com.deepwares.fishmarketplaceconsumer.ui.purchase

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.amplifyframework.api.ApiException
import com.amplifyframework.api.graphql.model.ModelMutation
import com.amplifyframework.core.Amplify
import com.amplifyframework.datastore.generated.model.Inventory
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.model.FishRepository
import com.deepwares.fishmarketplaceconsumer.ui.listing.ListingsViewModel
import com.deepwares.fishmarketplaceconsumer.ui.purchase.PurchaseFragmentArgs
import kotlinx.android.synthetic.main.fragment_purchase.*
import kotlinx.android.synthetic.main.fragment_purchase.image
import kotlinx.android.synthetic.main.fragment_purchase.name
import kotlinx.android.synthetic.main.inventory_fragment.*

class PurchaseFragment : Fragment() {

    val TAG = PurchaseFragment::class.java.name
    val args: PurchaseFragmentArgs by navArgs()
    var item: Inventory? = null
    var species: Species? = null
    val handler = Handler(Looper.getMainLooper())
    private lateinit var listingsViewModel: ListingsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_purchase, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        listingsViewModel =
            ViewModelProvider(requireActivity()).get(ListingsViewModel::class.java)

        listingsViewModel.items.observe(viewLifecycleOwner, Observer {
            item = it[args.order]
            species = FishRepository.species[item!!.species]
            update()
        })
        order.setOnClickListener {
            order()
        }
        cancel.setOnClickListener { findNavController()?.popBackStack() }
    }

    fun order() {
        val qtyAmount = quantity.text.toString().toInt()
        val max = item!!.availableQuantity
        if (max == 0) {
            Toast.makeText(context, R.string.order_empty, Toast.LENGTH_LONG).show()

        } else if (qtyAmount == 0) {
            Toast.makeText(context, R.string.order_min, Toast.LENGTH_LONG).show()

        } else if (qtyAmount > max) {
            Toast.makeText(context, R.string.order_excess, Toast.LENGTH_LONG).show()

        } else {
            val inventory = Inventory.Builder()
            inventory.id(item!!.id)
            inventory.availableQuantity(max - qtyAmount)
            Amplify.API.mutate(
                ModelMutation.update(inventory.build()),
                { response ->
                    handler.post {
                        Toast.makeText(context, R.string.order_success, Toast.LENGTH_LONG).show()
                        findNavController().navigate(R.id.navigation_dashboard)
                    }

                    Log.d(
                        TAG,
                        "Added Inventory with id: " + response?.data?.id
                    )
                },
                { error: ApiException? ->
                    handler.post {
                        Toast.makeText(context, R.string.order_fail, Toast.LENGTH_LONG).show()

                    }
                    Log.e(TAG, "Create Inventory failed", error)
                }
            )
        }
    }

    private fun update() {
        seller.text = "Deepak The Sailor man"
        seller_rating.text = "4.3"
        sell_location.setText(item!!.sellLocation)
        catch_location.setText(item!!.catchLocation)
        image.setImageResource(species!!.image)
        price.setText(item!!.price.toString())
        availability.setText(item!!.availableQuantity.toString())
        name.setText(species!!.name)
    }
}