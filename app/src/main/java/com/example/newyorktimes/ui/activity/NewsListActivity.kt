package com.example.newyorktimes.ui.activity

import android.os.Bundle
import com.example.newsdaily.R
import com.example.newyorktimes.ui.fragments.NewsListFragment
import com.example.newyorktimes.ui.base.BaseActivity

class NewsListActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpView()
    }

    override fun getLayout(): Int = R.layout.activity_news_list

    private fun setUpView() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, NewsListFragment())
            .commit()
    }
}