package com.example.newyorktimes.di.module

import com.example.newyorktimes.ui.viewModels.MostPopularNewsViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MostPopularNewsViewModel(mostPopularNewsRepository = get())
    }
}
