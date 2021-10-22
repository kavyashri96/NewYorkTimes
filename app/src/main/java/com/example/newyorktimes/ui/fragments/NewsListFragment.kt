package com.example.newyorktimes.ui.fragments

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsdaily.R
import com.example.newyorktimes.data.models.News
import com.example.newyorktimes.ui.adapter.NewsListAdapter
import com.example.newyorktimes.ui.base.BaseFragment
import com.example.newyorktimes.ui.viewModels.MostPopularNewsViewModel
import com.example.newyorktimes.utlis.Resource
import com.example.newyorktimes.utlis.Status
import kotlinx.android.synthetic.main.fragment_news_list.*
import org.koin.android.viewmodel.ext.android.viewModel

class NewsListFragment : BaseFragment() {

    private lateinit var adapter: NewsListAdapter
    private val popularNewsViewModel: MostPopularNewsViewModel by viewModel()

    override fun getLayout(): Int = R.layout.fragment_news_list

    override fun setUpView(view: View) {
        observerNewsData()
        popularNewsViewModel.getMostPopularNews()
        setUpAdapter()
    }

    private fun observerNewsData() {
        popularNewsViewModel.newsResult.removeObservers(viewLifecycleOwner)
        popularNewsViewModel.newsResult.observe(viewLifecycleOwner) { handleData(it) }
    }

    private fun handleData(it: Resource<List<News>>?) {
        when (it?.status) {
            Status.SUCCESS -> {
                it.data?.let { newsList ->
                    renderList(newsList = newsList)
                    recycler_view?.visibility = View.VISIBLE
                    progress_bar?.visibility = View.GONE
                }
            }
            Status.LOADING -> {
                progress_bar?.visibility = View.VISIBLE
                recycler_view?.visibility = View.GONE
            }
            Status.ERROR -> {
                Toast.makeText(activity, it.message, Toast.LENGTH_LONG).show()
                recycler_view?.visibility = View.GONE
                progress_bar?.visibility = View.GONE
                error_message.visibility = View.VISIBLE
            }
        }
    }

    private fun renderList(newsList: List<News>) {
        adapter.addData(newsList)
    }

    private fun setUpAdapter() {
        recycler_view?.layoutManager = LinearLayoutManager(activity)
        adapter = NewsListAdapter(
            itemClickListener = {
                launchDetailFragment()
            }
        )
        recycler_view?.itemAnimator = DefaultItemAnimator()
        recycler_view?.adapter = adapter
    }

    private fun launchDetailFragment() {
        activity?.supportFragmentManager!!
            .beginTransaction()
            .replace(R.id.fragment_container, NewsDetailFragment())
            .commit()
    }
}