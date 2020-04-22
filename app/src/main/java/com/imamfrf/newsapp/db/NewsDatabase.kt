package com.imamfrf.newsapp.db

import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.imamfrf.newsapp.model.News


//Database annotation to specify the entities and set version
@Database(entities = [News::class], version = 1)
abstract class NewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDao
    companion object {
        private var instance: NewsDatabase? = null
        fun getInstance(context: Context): NewsDatabase {
            if (instance == null) {
                synchronized(NewsDatabase::class) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsDatabase::class.java, "news_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                }
            }
            return instance!!
        }


        fun destroyInstance() {
            instance = null
        }

        private val roomCallback = object : RoomDatabase.Callback(){
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                AsyncTask.execute {
                    val newsDao = instance?.newsDao()
                    newsDao?.insert(News(
                        title = "Arsenal dan Spurs Berpeluang Gusur Chelsea di Liga Champions",
                        publisher = "CNN Indonesia",
                        image = "https://akcdn.detik.net.id/visual/2019/12/29/b13cdb4d-1ed4-40ca-9248-5533dcfaea12_169.jpeg?w=650"))
                    newsDao?.insert(News(
                        title = "Ian Rush: Liverpool Era Klopp Bisa Jadi yang Terhebat",
                        publisher = "CNN Indonesia",
                        image = "https://akcdn.detik.net.id/visual/2019/12/30/1ed27821-95ec-4d1a-aacb-54237a43144f_169.jpeg?w=650"))
                    newsDao?.insert(News(
                        title = "Lukaku Ungkap 23 Pemain Inter Sempat Alami Gejala Covid-19",
                        publisher = "CNN Indonesia",
                        image = "https://akcdn.detik.net.id/visual/2019/08/27/07263a21-f265-4e8a-947d-a11df7b2de1b_169.jpeg?w=650"))

                }
            }
        }
    }


}
