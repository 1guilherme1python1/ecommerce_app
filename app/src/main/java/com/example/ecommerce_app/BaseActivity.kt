package com.example.ecommerce_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.Firebase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database

open class BaseActivity : AppCompatActivity() {

    protected val database by lazy {
        FirebaseDatabase.getInstance()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
}