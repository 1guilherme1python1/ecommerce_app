package com.example.ecommerce_app.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.ecommerce_app.databinding.ActivityMainBinding
import com.example.ecommerce_app.viewmodel.MainViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.ecommerce_app.BaseActivity
import com.example.ecommerce_app.R
import com.example.ecommerce_app.views.adapter.CategoryAdapter
import com.example.ecommerce_app.views.adapter.ItemAdapter
import com.example.ecommerce_app.views.adapter.SliderAdapter

class MainActivity : BaseActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var mainViewModel: MainViewModel

    private lateinit var recyclerViewBanner: ViewPager2
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var recyclerViewItem: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    fun initView(){
        mainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initBanner()
        initCategory()
        initItems()
        initTabNavigation()
    }

    private fun initTabNavigation() {
        binding.btnViewCart.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
    }

    fun initBanner(){
        mainViewModel.listaBanner.observe(this) { lista ->
            if (lista.isNotEmpty()) {
                binding.progressBarBanner.visibility = View.GONE
                lista?.let {
                    recyclerViewBanner = findViewById<ViewPager2>(R.id.view_page_slider)

                    recyclerViewBanner.adapter = SliderAdapter(lista)

                    recyclerViewBanner.clipToPadding = false
                    recyclerViewBanner.clipChildren = false
                    recyclerViewBanner.offscreenPageLimit = 3
                    recyclerViewBanner.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

                    val compositePageTransforme = CompositePageTransformer()
                    compositePageTransforme.addTransformer(MarginPageTransformer(40))

                    binding.viewPageSlider.setPageTransformer(compositePageTransforme)
                }
            } else {
                binding.progressBarBanner.visibility = View.VISIBLE
            }
        }
    }

    private fun initCategory() {
        mainViewModel.listaCategories.observe(this){lista ->
            if(lista.isNotEmpty()){
                binding.progressBarBrandOfficial.visibility = View.GONE

                lista?.let {
                    recyclerViewCategory = findViewById(R.id.rv_brands_official)

                    recyclerViewCategory.adapter = CategoryAdapter(lista)

                    recyclerViewCategory.layoutManager = LinearLayoutManager(
                        this,
                        LinearLayoutManager.HORIZONTAL,
                        false
                        )
                }

            } else {
                binding.progressBarBanner.visibility = View.VISIBLE
            }
        }
    }

    fun initItems(){
        mainViewModel.listaItems.observe(this){lista ->
            if(lista.isNotEmpty()){
                binding.progressBarProductsPop.visibility = View.GONE

                lista?.let {
                    recyclerViewItem = findViewById(R.id.rv_products_populares)

                    recyclerViewItem.adapter = ItemAdapter(lista)

                    recyclerViewItem.layoutManager = GridLayoutManager(this, 2)

                }
            }else {
                binding.progressBarProductsPop.visibility = View.VISIBLE
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mainViewModel.recuperarBanners()
        mainViewModel.recuperarCategories()
        mainViewModel.recuperarItems()
    }
}