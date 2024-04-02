package com.example.ecommerce_app.services

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

object FirebaseService {
    fun recuperaDataBaseFirebase(): DatabaseReference{
        return FirebaseDatabase.getInstance().reference
    }
}