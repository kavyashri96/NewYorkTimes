package com.example.newyorktimes.ui.fragments

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsdaily.R
import com.example.newyorktimes.ui.adapter.NewsListAdapter
import com.example.newyorktimes.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsListFragment : BaseFragment() {

    private lateinit var adapter: NewsListAdapter

    override fun getLayout(): Int = R.layout.fragment_news_list

    override fun setUpView(view: View) {
        setUpAdapter()
    }

    private fun setUpAdapter() {
        recycler_view?.layoutManager = LinearLayoutManager(activity)
        adapter = NewsListAdapter(
            itemClickListener = {
                Toast.makeText(requireContext(), "HELOOOO", Toast.LENGTH_LONG).show()
            }
        )
        recycler_view?.itemAnimator = DefaultItemAnimator()
        recycler_view?.adapter = adapter
    }
}