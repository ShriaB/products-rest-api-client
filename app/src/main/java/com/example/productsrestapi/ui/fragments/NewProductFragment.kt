package com.example.productsrestapi.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.productsrestapi.databinding.FragmentNewProductBinding
import com.example.productsrestapi.ui.ProductsViewModel
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewProductFragment : Fragment() {

    lateinit var binding: FragmentNewProductBinding
    private val viewModel by activityViewModel<ProductsViewModel>()
    var itemId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.res.value = ""
        arguments?.apply {
            itemId = getInt("itemId")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewProductBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.apply {
            if(itemId > 0){
                saveBtn.text = "Update"
                deleteBtn.visibility = View.VISIBLE
            }
            if(itemId > 0) {
                inputTitle.setText(viewModel.product.value?.title)
                inputBrand.setText(viewModel.product.value?.brand)
                inputDesc.setText(viewModel.product.value?.description)
                inputStock.setText(viewModel.product.value?.stock.toString())
                inputPrice.setText(viewModel.product.value?.price.toString())
                saveBtn.setOnClickListener {
                    viewModel.updateProduct(
                        itemId,
                        inputTitle.text.toString(),
                        inputBrand.text.toString(),
                        inputDesc.text.toString(),
                        inputStock.text.toString().toInt(),
                        inputPrice.text.toString().toInt()
                    )
                }
            }
            else{
                saveBtn.setOnClickListener {
                    viewModel.addNewProduct(
                        inputTitle.text.toString(),
                        inputBrand.text.toString(),
                        inputDesc.text.toString(),
                        inputStock.text.toString().toInt(),
                        inputPrice.text.toString().toInt()
                    )
                }
            }
            deleteBtn.setOnClickListener{
                viewModel.deleteProduct(itemId)
            }
        }
    }
}