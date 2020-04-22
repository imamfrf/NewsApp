package com.imamfrf.newsapp.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.imamfrf.newsapp.R
import com.imamfrf.newsapp.model.News
import com.imamfrf.newsapp.ui.adapter.NewsAdapter
import com.imamfrf.newsapp.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {
    companion object{
        private const val ADD_NEWS_REQUEST = 1
    }

    private val newsViewModel: NewsViewModel by inject()
    private val adapter: NewsAdapter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupButtonAddNote()
        setupRecyclerView()
        newsViewModel.getAllNews().observe(this,
            Observer<List<News>> { list ->
                list?.let {
                    adapter.setNews(it)
                }
            })
    }

    private fun setupButtonAddNote() {
        buttonAddNote.setOnClickListener {
            startActivityForResult(
                Intent(this, AddNewsActivity::class.java),
                ADD_NEWS_REQUEST
            )
        }
    }

    private fun setupRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
        recycler_view.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.delete_all_news -> {
                newsViewModel.deleteAllNews()
                Toast.makeText(this, "All news deleted!", Toast.LENGTH_SHORT).show()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_NEWS_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            val newNote = News(
                title = data.getStringExtra(AddNewsActivity.EXTRA_TITLE),
                publisher = data.getStringExtra(AddNewsActivity.EXTRA_PUBLISHER),
                image = data.getStringExtra(AddNewsActivity.EXTRA_IMAGE)
            )
            newsViewModel.insert(newNote)

            Toast.makeText(this, "News saved!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "News not saved!", Toast.LENGTH_SHORT).show()
        }
    }


}


