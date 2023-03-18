package com.example.productsrestapi.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import coil.load
import com.example.productsrestapi.R
import com.example.productsrestapi.databinding.FragmentProductDetailBinding
import com.example.productsrestapi.model.ProductsItem
import com.example.productsrestapi.ui.ProductsViewModel
import com.example.productsrestapi.ui.ProductsViewModelFactory

class ProductDetailFragment : Fragment() {

    private val viewModel: ProductsViewModel by activityViewModels {
        ProductsViewModelFactory()
    }

    private lateinit var binding: FragmentProductDetailBinding
    private var itemId: Int = 0
//    private lateinit var product: ProductsItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemId = arguments?.getInt("itemId")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        product = viewModel.product.value!!
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.product.observe(this.viewLifecycleOwner) {
            binding.product = viewModel.product.value
            binding.lifecycleOwner = this
        }

        binding.editBtn.setOnClickListener{
            view.findNavController().navigate(R.id.action_productDetailFragment_to_newProductFragment,
            bundleOf( "itemId" to itemId)
            )
        }
    }
}