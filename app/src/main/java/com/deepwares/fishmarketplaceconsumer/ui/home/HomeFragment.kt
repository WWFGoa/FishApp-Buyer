package com.deepwares.fishmarketplaceconsumer.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.deepwares.fishmarketplace.interfaces.SpeciesSelector
import com.deepwares.fishmarketplace.model.Species
import com.deepwares.fishmarketplace.ui.creator.SpeciesAdapter
import com.deepwares.fishmarketplaceconsumer.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), SpeciesSelector {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var createViewModel: CreateViewModel
    private var speciesSelector: SpeciesSelector? = null
    private lateinit var speciesAdapter: SpeciesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        speciesSelector = activity as SpeciesSelector
        speciesAdapter = SpeciesAdapter(speciesSelector)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createViewModel =
            ViewModelProvider(requireActivity()).get(CreateViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val padding = resources.getDimensionPixelSize(R.dimen.padding_standard)
        list.addItemDecoration(object : RecyclerView.ItemDecoration() {
            override fun getItemOffsets(
                outRect: Rect,
                view: View,
                parent: RecyclerView,
                state: RecyclerView.State
            ) {
                val index = parent.getChildAdapterPosition(view)
                val pos = index % 3
                if (pos == 0) {
                    outRect.left = padding*2
                    outRect.right = padding
                } else if (pos == 2){
                    outRect.left = padding
                    outRect.right = padding*2
                }else{
                    outRect.left = padding
                    outRect.right = padding
                }
                outRect.top = padding*2

            }
        })
        list.adapter = speciesAdapter
        list.layoutManager = GridLayoutManager(context, 3)
        createViewModel.speciesLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                speciesAdapter.species.clear()
                speciesAdapter.species.addAll(it)
                speciesAdapter.notifyDataSetChanged()

            }
        })

    }

    override fun onDestroy() {
        speciesSelector = null
        speciesAdapter.speciesSelector = null
        super.onDestroy()
    }

    override fun selectSpecies(species: Species, position: Int) {
    }
}