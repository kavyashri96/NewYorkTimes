package com.example.newyorktimes.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsdaily.R

class NewsListAdapter(
    private val itemClickListener: (() -> Unit)? = null,
) : RecyclerView.Adapter<NewsListAdapter.DataViewHolder>() {

    // declare a list

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news_card, parent, false)
        return DataViewHolder(view)
    }

    override fun getItemCount(): Int = 10

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind()
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

    class DataViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        fun bind() {}
    }

    // add items to the list
    fun addData() {
        notifyDataSetChanged()
    }
}

enum class ACTION {
    BOOKMARK,
    CARD_CLICK
}
