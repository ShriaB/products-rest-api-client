package com.example.productsrestapi

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ProductsApiApplication: Application(){

    /**
     * Initializing Koin in Application
     * Passing the application in context
     * and the modules where the dependencies are declared
     */
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@ProductsApiApplication)
            modules(appModule, activityModule)
        }
    }
}