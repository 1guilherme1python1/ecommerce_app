package com.example.ecommerce_app.views.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerce_app.R
import com.example.ecommerce_app.viewmodel.ReviewFragmentModel
import com.example.ecommerce_app.views.adapter.ReviewAdapter

class ReviewFragment : Fragment() {

    private lateinit var reviewFragmentModel: ReviewFragmentModel

    private lateinit var recyclerViewReview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.fragment_review, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFragment(view)
    }

    private fun initFragment(view: View) {
        reviewFragmentModel = ViewModelProvider(this)[ReviewFragmentModel::class.java]

        reviewFragmentModel.recuperarReviews()

        initList(view)
    }

    private fun initList(view: View) {
        reviewFragmentModel.listaReview.observe(viewLifecycleOwner){lista->
            if(lista.isNotEmpty()){
                recyclerViewReview = view.findViewById(R.id.rvReview)

                recyclerViewReview.adapter = ReviewAdapter(lista)

                recyclerViewReview.layoutManager = LinearLayoutManager(
                    this.context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            }
        }
    }
}