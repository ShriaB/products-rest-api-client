package com.example.productsrestapi.ui.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.productsrestapi.databinding.GridListItemBinding
import com.example.productsrestapi.model.ProductsItem

class ProductListAdapter(private val onClicked: (ProductsItem)->Unit): ListAdapter<ProductsItem, ProductListAdapter.ProductViewHolder>(DiffCallback) {
    inner class ProductViewHolder(val binding: GridListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(product: ProductsItem){
            binding.product = product
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return ProductViewHolder(GridListItemBinding.inflate(
            LayoutInflater.from(parent.context)
        ))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
        holder.itemView.setOnClickListener{
            onClicked(product)
        }
    }

    companion object DiffCallback: DiffUtil.ItemCallback<ProductsItem>(){
        override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
            return (oldItem.id == newItem.id)
        }

        override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
           return (oldItem.title == newItem.title)
        }

    }
}