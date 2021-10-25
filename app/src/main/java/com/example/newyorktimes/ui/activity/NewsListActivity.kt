package com.example.newyorktimes.ui.activity

import android.content.ContentResolver
import android.content.Intent
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newsdaily.R
import com.example.newsdaily.databinding.ActivityNewsListBinding
import com.example.newyorktimes.ui.fragments.NewsListFragment
import java.io.File
import java.io.FileOutputStream

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options, menu)
        return true
    }

    @Throws(Resources.NotFoundException::class)
    fun getUriToResource(
        res: Resources,
        resId: Int
    ): Uri {
        return Uri.parse(
            ContentResolver.SCHEME_ANDROID_RESOURCE + "://" + res.getResourcePackageName(resId) + '/' + res.getResourceTypeName(resId) + '/' + res.getResourceEntryName(
                resId
            )
        )
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bookmark -> {
                val imageIntent = Intent(Intent.ACTION_SEND)
                imageIntent.type = "image/png"

                startActivity(Intent.createChooser(imageIntent, "Share image using"));
                Toast.makeText(applicationContext, "Item 1 Selected", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}