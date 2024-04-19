package com.example.ecommerce_app.services

import android.content.Context
import android.util.Log
import com.example.ecommerce_app.model.Item
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharedPreferencesService {
    private const val PREFS_NAME = "carts_prefs"
    private const val KEY_PRODUCTS_IDS = "item_key"
    private val gson = Gson()

    fun addProductsToCart(context: Context, item: Item){
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

       val listProducts = (getProductsToCart(context) ?: mutableListOf()).toMutableList();

        if(listProducts.isNotEmpty()){
            listProducts+=item

            val listProductsJson = gson.toJson(listProducts)

            val jsonWithBrackets = "$listProductsJson"

            sharedPreferences.edit().putString(KEY_PRODUCTS_IDS, jsonWithBrackets).apply()

            Log.i("sharedPrefProd", "Cheia $listProductsJson")

        } else {
            val json = gson.toJson(item)
            sharedPreferences.edit().putString(KEY_PRODUCTS_IDS, "[$json]").apply()

            Log.i("sharedPrefProd", "Vazia $json")
        }

    }

    fun getProductsToCart(context: Context): List<Item>? {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val gson = Gson()
        val items = mutableListOf<Item>()

        sharedPreferences.all.entries.forEach { entry ->
            val key = entry.key
            if (key.startsWith(KEY_PRODUCTS_IDS)) {
                val json = entry.value as? String
                json?.let {
                    val itemList = gson.fromJson<List<Item>>(json, object : TypeToken<List<Item>>() {}.type)
                    itemList?.let {
                        items.addAll(itemList)
                    }
                }
            }
        }

        return items
    }
}