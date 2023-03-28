package com.example.productsrestapi

import org.koin.core.qualifier.named
import org.koin.dsl.module

val activityModule = module{
    /**
     * Can also be declared in the appModule
     * But for better separation we define a different module for activity scoped dependencies
     */
    scope<MainActivity> {
        //Qualifiers resolve ambiguity if there are multiple dependencies of the same type
        scoped(qualifier = named("hello")) {"Hello"}
        scoped(qualifier = named("bye")) {"Bye"}
    }
}