package com.example.ecommerce_app.model

import com.example.ecommerce_app.services.FirebaseService
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class ItemFirebase {
    suspend fun recuperarItems(): List<Item> = suspendCoroutine {continuation ->
        val dataBase = FirebaseService.recuperaDataBaseFirebase()

        dataBase.child("Items").get().addOnSuccessListener {itemSnapshot ->
            var items = mutableListOf<Item>()

            for(item in itemSnapshot.children){
                val itemModel = item.getValue(Item::class.java)

                itemModel?.let {
                    items.add(itemModel)
                }
            }

            continuation.resume(items)

        }.addOnFailureListener {exception ->
            exception.printStackTrace()
            continuation.resume(emptyList())
        }
    }
}