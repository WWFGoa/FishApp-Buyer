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
import com.amplifyframework.datastore.generated.model.Order
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplaceconsumer.R
import com.deepwares.fishmarketplaceconsumer.model.FishRepository
import com.deepwares.fishmarketplaceconsumer.ui.listing.ListingsViewModel
import com.deepwares.fishmarketplaceconsumer.ui.login.afterTextChanged
import kotlinx.android.synthetic.main.fragment_purchase.*
import kotlinx.android.synthetic.main.fragment_purchase.image
import kotlinx.android.synthetic.main.fragment_purchase.name
import kotlinx.android.synthetic.main.inventory_fragment.*
import java.util.*

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
        quantity.setListener {
            val qtyAmount = it
            val price = item!!.price
            total.text = (qtyAmount * price).toString()
        }
        /*
        quantity.afterTextChanged {
            val qty = quantity.text.toString()
            val qtyAmount = if (qty.isNotBlank() && qty.isNotEmpty()) qty.toInt() else 0
            val price = item!!.price
            total.text = (qtyAmount * price).toString()
        }
        quantity.setOnEditorActionListener { v, actionId, event ->

            val qtyAmount = quantity.text.toString().toInt()
            val price = item!!.price
            total.text = (qtyAmount * price).toString()
            return@setOnEditorActionListener true
        }
*/

    }

    fun order() {
        val qtyAmount = 0
        // val qtyAmount = quantity.text.toString().toFloat()
        val max = item!!.availableQuantity
        if (max == 0f) {
            Toast.makeText(context, R.string.order_empty, Toast.LENGTH_LONG).show()

        } else if (qtyAmount <= 0f) {
            Toast.makeText(context, R.string.order_min, Toast.LENGTH_LONG).show()

        } else if (qtyAmount > max) {
            Toast.makeText(context, R.string.order_excess, Toast.LENGTH_LONG).show()

        } else {
            val inventory = item!!.copyOfBuilder()
            val available = max - qtyAmount
            inventory.availableQuantity(available)
            val createdInv = inventory.build()


            Amplify.API.mutate(
                ModelMutation.update(createdInv),
                { response ->


                    Log.d(
                        TAG,
                        "Added Inventory with id: " + response?.data?.id
                    )

                    createOrder(inventory = createdInv, quantity = qtyAmount.toFloat())
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

    fun createOrder(inventory: Inventory, quantity: Float) {
        val order = Order.Builder()
        order.id(UUID.randomUUID().toString())
        order.quantity(quantity)
        order.inventory(inventory)
        val user = Amplify.Auth.currentUser
        order.contact(user.username + " " + user.userId)

        Amplify.API.mutate(
            ModelMutation.create(order.build()),
            { response ->

                handler.post {
                    Toast.makeText(context, R.string.order_success, Toast.LENGTH_LONG).show()
                    findNavController().navigate(R.id.navigation_orders)
                }

                Log.d(
                    TAG,
                    "Created Order  with id: " + response?.data?.id
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


    private fun update() {
        seller.text = item!!.contact
        // seller_rating.text = "4.3"
        sell_location.setText(item!!.sellLocation)
        catch_location.setText(item!!.catchLocation)
        image.setImageResource(species!!.image)
        price.setText(item!!.price.toString())

        availability.setText(item!!.availableQuantity.toString())
        //findNavController().currentDestination?.label = resources.getString(species!!.name)
        name.setText(species!!.name)

        quantity.minValue = 1
        quantity.maxValue = item!!.availableQuantity.toInt()

    }
}