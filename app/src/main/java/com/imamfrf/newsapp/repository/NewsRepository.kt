package com.imamfrf.newsapp.repository

import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.imamfrf.newsapp.db.NewsDao
import com.imamfrf.newsapp.model.News

class NewsRepository(val newsDao: NewsDao) {
    private val allNews = newsDao.getAllNews()

    fun insert(news: News) {
        AsyncTask.execute {
            newsDao.insert(news)
        }
    }

    fun deleteAllNews() {
        AsyncTask.execute {
            newsDao.deleteAllNews()
        }
    }

    fun getAllNews(): LiveData<List<News>> {
        return allNews
    }

}