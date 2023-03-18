package com.example.productsrestapi.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.productsrestapi.model.GetResponse
import com.example.productsrestapi.model.ProductsItem
import com.example.productsrestapi.network.ProductsApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.launch
import java.util.*


class ProductsViewModel: ViewModel() {

    var res = MutableLiveData<String>()
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()
    var product = MutableLiveData<ProductsItem>()
     var allProductsItems = MutableLiveData<List<ProductsItem>?>()

    fun getAllProducts(){
        viewModelScope.launch{
            try{
                val response = ProductsApi.retrofitService.getProducts()
                if(response.isSuccessful) {
                    allProductsItems.value = response.body()?.products

//                    Log.d("MyDebug", "${allProductsItems.value}")
                }
            }catch (e: Exception){
                Log.d("MyDebug", "Failure: ${e.message}")
            }
        }
    }

    fun getSingleProduct(id: Int){
        viewModelScope.launch{
            try{
                val response = ProductsApi.retrofitService.getProduct(id)
                if(response.isSuccessful) {
                    product.value = response.body()
//                    Log.d("MyDebug", "res: ${product.value}")
                }
            }catch (e: Exception){
                Log.d("MyDebug", "Failure: ${e.message}")
            }
        }
    }

    fun getProductItem(title: String, brand: String, description: String, stock: Int, price: Int): ProductsItem{
        return ProductsItem(
            title = title,
            brand = brand,
            description = description,
            stock = stock,
            price = price
        )
    }

    fun addNewProduct(title: String, brand: String, description: String, stock: Int, price: Int){
        // Object
        val newProduct = getProductItem(title, brand, description, stock, price)

        viewModelScope.launch {
            try{
                val response = ProductsApi.retrofitService.createProduct(newProduct)
                if(response.isSuccessful) {
                    res.value = gson.toJson(
                        JsonParser.parseString(
                            response.body()?.string()
                        )
                    )
                    Log.d("MyDebug", "Success: ${res.value}")
                }
            }catch (e: Exception){
                Log.d("MyDebug", "Failure: ${e.message}")
            }
        }
    }

    fun getUpdatedFieldMap(title: String, brand: String, description: String, stock: Int, price: Int): Map<String, String>{
        return mapOf(
            "title" to title,
            "brand" to brand,
            "description" to description,
            "stock" to stock.toString(),
            "price" to price.toString()
        )
    }

    fun updateProduct(id: Int, title: String="", brand: String="", description: String="", stock: Int=0, price: Int=0){

        val updatedProduct = getUpdatedFieldMap(title, brand, description, stock, price)
        Log.d("MyDebug", "In updateProduct")

        viewModelScope.launch {
            try{
                val response = ProductsApi.retrofitService.updateProduct(id, updatedProduct)
                if(response.isSuccessful) {
                    res.value = gson.toJson(
                        JsonParser.parseString(
                            response.body()?.string()
                        )
                    )
                    Log.d("MyDebug", "Success: ${res.value}")
                }
            }catch (e: Exception){
                Log.d("MyDebug", "Failure: ${e.message}")
            }
        }
    }

    fun deleteProduct(id: Int){
        viewModelScope.launch {
            try{
                val response = ProductsApi.retrofitService.deleteProduct(id)
                if(response.isSuccessful) {
                    res.value = gson.toJson(
                        JsonParser.parseString(
                            response.body()?.string()
                        )
                    )
                    Log.d("MyDebug", "Success: ${res.value}")
                }
            }catch (e: Exception){
                Log.d("MyDebug", "Failure: ${e.message}")
            }
        }
    }

    init {
        getAllProducts()
    }
}


class ProductsViewModelFactory: ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProductsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProductsViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}