package com.example.newyorktimes.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.newsdaily.R
import com.example.newsdaily.databinding.ActivityNewsListBinding
import com.example.newyorktimes.ui.fragments.NewsListFragment

class NewsListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpView()
    }

    private fun setUpView() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_container, NewsListFragment())
            .commit()
    }
}