package com.example.newyorktimes.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdaily.databinding.ItemNewsCardBinding
import com.example.newyorktimes.data.models.News

class NewsListAdapter(
    private val itemClickListener: (() -> Unit)? = null,
) : RecyclerView.Adapter<NewsListAdapter.DataViewHolder>() {

    private lateinit var binding: ItemNewsCardBinding

    // declare a list
    private val newsList: ArrayList<News> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        binding = ItemNewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        // val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_card, parent, false)
        return DataViewHolder(binding.root)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(newsList[position])
        setViewClickListener(holder, position)
    }

    private fun setViewClickListener(
        holder: DataViewHolder,
        position: Int
    ) {
        holder.itemView.setOnClickListener {
            itemClickListener?.invoke()
        }
    }

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemNewsCardBinding.bind(itemView)
        fun bind(news: News) {
            with(binding) {
                articleTitle.text = news.title
                articleAuthor.text = news.subsection
            }
        }
    }

    // add items to the list
    fun addData(list: List<News>) {
        newsList.addAll(list)
        notifyDataSetChanged()
    }
}

enum class ACTION {
    BOOKMARK,
    CARD_CLICK
}
