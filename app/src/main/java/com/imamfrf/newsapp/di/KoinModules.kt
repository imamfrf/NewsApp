package com.imamfrf.newsapp.di

import com.imamfrf.newsapp.db.NewsDatabase
import com.imamfrf.newsapp.repository.NewsRepository
import com.imamfrf.newsapp.ui.adapter.NewsAdapter
import com.imamfrf.newsapp.viewmodel.NewsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


val dbModule = module {
    single {
        NewsDatabase.getInstance(
            context = get()
        )
    }
    factory { get<NewsDatabase>().newsDao() }
}

val repositoryModule = module {
    single {
        NewsRepository(get())
    }
}
val uiModule = module {
    factory { NewsAdapter() }
    viewModel { NewsViewModel(get()) }
}