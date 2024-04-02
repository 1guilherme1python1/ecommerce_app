package com.example.ecommerce_app.views

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce_app.BaseActivity
import com.example.ecommerce_app.R
import com.example.ecommerce_app.databinding.ActivityDatailBinding
import com.example.ecommerce_app.model.Banner
import com.example.ecommerce_app.model.Item
import com.example.ecommerce_app.views.adapter.SizeAdapter
import com.example.ecommerce_app.views.adapter.SliderAdapter
import com.google.android.material.slider.Slider

class DatailActivity : BaseActivity() {

    private lateinit var itemObject: Item

    private val binding by lazy {
        ActivityDatailBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        getBundle()
        banners()
        initSize()

    }

    private fun initSize() {
        val listSize = listOf(
            "P",
            "M",
            "G",
            "XG",
            "XS"
        )

        binding.rvSize.adapter = SizeAdapter(listSize)
        binding.rvSize.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )
    }

    private fun banners(): List<Banner> {
        val listImages =
            ArrayList<Banner>() // Aqui você adiciona os itens que serão exibidos no ViewPager

        for (i in 0 until itemObject.picUrl.size) {
            val imageUrl = itemObject.picUrl[i]
            listImages.add(Banner(imageUrl))
        }

        return listImages
    }

    private fun getBundle() {
        val extras = intent.extras

        if (extras != null) {

            itemObject = (if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                extras?.getParcelable<Item>("objectItem")
            } else {
                extras?.getParcelable("objectItem")
            } as? Item)!!


            with(itemObject) {
                binding.textTitleDetail.text = itemObject.title
                binding.textPriceDetail.text = "R$ " + itemObject.price.toString()
                binding.ratingBarDetail.rating = itemObject.rating.toFloat()
                binding.textRatingAvaliacao.text = itemObject.rating.toString() + " avaliacao"

                binding.imgViewDetail.adapter = SliderAdapter(banners())
                binding.imgViewDetail.clipToPadding = false
                binding.imgViewDetail.clipChildren = false
                binding.imgViewDetail.offscreenPageLimit = 3
                binding.imgViewDetail.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER


                binding.btnBackDetail.setOnClickListener {
                    finish()
                }

            }
        }
    }

    private fun setupViewPager(){
        val adapter: ViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)

    }

    private class ViewPagerAdapter(
        fragmenteManager: FragmentManager,
        ) : FragmentPagerAdapter(
            fragmenteManager,
        ) {
        private val mFragmentList = mutableListOf<Fragment>()
        private val mFragmentTitleList = mutableListOf<String>()

        override fun getCount(): Int {
            return mFragmentList.size
        }

        override fun getItem(position: Int): Fragment {
            return mFragmentList.get(position)
        }

        private fun addFrag(fragment: Fragment, title: String){
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence{
            return mFragmentTitleList.get(position)
        }

    }


}