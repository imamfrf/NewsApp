package com.imamfrf.newsapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.imamfrf.newsapp.model.News

@Dao
interface NewsDao {
    @Insert
    fun insert(news: News)

    @Query("SELECT * FROM news")
    fun getAllNews() : LiveData<List<News>>

    @Query("DELETE FROM news")
    fun deleteAllNews()
}