package com.covid19

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class SearchDetail : Fragment() {

    companion object {
        fun newInstance() = SearchDetail()
    }

    private lateinit var viewModel: SearchDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_detail_fragment, container, false)
    }



}