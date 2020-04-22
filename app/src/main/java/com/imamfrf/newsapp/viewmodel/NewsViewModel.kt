package com.imamfrf.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.imamfrf.newsapp.model.News
import com.imamfrf.newsapp.repository.NewsRepository

class NewsViewModel(private var repository: NewsRepository) : ViewModel() {
    private var allNews: LiveData<List<News>> = repository.getAllNews()

    fun insert(news: News) {
        repository.insert(news)
    }

    fun deleteAllNews() {
        repository.deleteAllNews()
    }

    fun getAllNews(): LiveData<List<News>> {
        return allNews
    }
}