package com.example.productsrestapi.model

import com.google.gson.annotations.SerializedName

data class GetResponse(@SerializedName("total")
                       val total: Int = 0,
                       @SerializedName("limit")
                       val limit: Int = 0,
                       @SerializedName("skip")
                       val skip: Int = 0,
                       @SerializedName("products")
                       val products: List<ProductsItem>?)