package com.imamfrf.newsapp.ui.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.imamfrf.newsapp.R
import com.imamfrf.newsapp.model.News

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {
    private var news: List<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return NewsHolder(itemView)
    }
    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val currentNews = news[position]
        holder.textViewTitle.text = currentNews.title
        holder.textViewPublisher.text = currentNews.publisher
        Glide.with(holder.itemView.context).load(currentNews.image).into(holder.imageThumbnail)
    }
    override fun getItemCount(): Int {
        return news.size
    }

    fun setNews(news: List<News>) {
        this.news = news
        notifyDataSetChanged()
    }
    inner class NewsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewTitle: TextView = itemView.findViewById(R.id.text_title)
        var textViewPublisher: TextView = itemView.findViewById(R.id.text_publisher)
        var imageThumbnail: ImageView = itemView.findViewById(R.id.image_thumbnail)
    }
}
