package com.example.productsrestapi

import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("imgSrcUrl")
fun bindImage(imageView: ImageView, imgSrcUrl: String?) {
    imgSrcUrl?.let{
        val imgUri = imgSrcUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imgUri)
    }
}

@BindingAdapter("textFromInt")
fun bindTextViewFromInt(textView: TextView, price: Int?){
    price?.let{
        if(price != 0)
            textView.text = price.toString()
    }
}