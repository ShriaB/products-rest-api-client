package com.example.productsrestapi.network

import com.example.productsrestapi.model.GetResponse
import com.example.productsrestapi.model.ProductsItem
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://dummyjson.com"

//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ProductsApiService {
    // Read
    @GET("/products")
    suspend fun getProducts(): Response<GetResponse>

    @GET("/products/{id}")
    suspend fun getProduct(@Path("id") itemId: Int): Response<ProductsItem>

    // Create
    @POST("/products/add")
    suspend fun createProduct(@Body productsItem: ProductsItem): Response<ResponseBody>

    // Update
    @FormUrlEncoded
    @PUT("/products/{id}")
    suspend fun updateProduct(@Path("id") itemId: Int, @FieldMap fields: Map<String, String>): Response<ResponseBody>

    // Delete
    @DELETE("/products/{id}")
    suspend fun deleteProduct(@Path("id") itemId: Int): Response<ResponseBody>
}

object ProductsApi{
    val retrofitService: ProductsApiService by lazy {
        retrofit.create(ProductsApiService::class.java)
    }
}