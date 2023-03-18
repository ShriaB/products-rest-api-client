package com.example.productsrestapi.model

import com.google.gson.annotations.SerializedName

data class ProductsItem(@SerializedName("discountPercentage")
                        val discountPercentage: Double = 0.0,
                        @SerializedName("thumbnail")
                        val thumbnail: String = "",
                        @SerializedName("images")
                        val images: List<String>? = listOf(),
                        @SerializedName("price")
                        val price: Int = 0,
                        @SerializedName("rating")
                        val rating: Double = 0.0,
                        @SerializedName("description")
                        val description: String = "",
                        @SerializedName("id")
                        val id: Int = 0,
                        @SerializedName("title")
                        val title: String = "",
                        @SerializedName("stock")
                        val stock: Int = 0,
                        @SerializedName("category")
                        val category: String = "",
                        @SerializedName("brand")
                        val brand: String = "")