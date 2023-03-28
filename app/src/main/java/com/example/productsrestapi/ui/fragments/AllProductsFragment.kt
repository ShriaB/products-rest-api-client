package com.example.productsrestapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import com.example.productsrestapi.R
import com.example.productsrestapi.databinding.FragmentAllProductsBinding
import com.example.productsrestapi.ui.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AllProductsFragment : Fragment() {

    private lateinit var binding: FragmentAllProductsBinding

    /**
     * Activity Shared VIewModel in fragments
     */
    private val viewModel by activityViewModel<ProductsViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAllProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // Instanciating the ProductListAdapter by passing in the click listener
        val adapter = ProductListAdapter{
            val args = bundleOf(
                    "itemId" to it.id,
            )
            view.findNavController().navigate(R.id.action_allProductsFragment_to_productDetailFragment, args)
        }
        binding.recyclerView.adapter = adapter
        viewModel.allProductsItems.observe(this.viewLifecycleOwner){
            adapter.submitList(viewModel.allProductsItems.value)
        }

        binding.addBtn.setOnClickListener{
            val action = AllProductsFragmentDirections.actionAllProductsFragmentToNewProductFragment()
            view.findNavController().navigate(action)
        }
    }
}