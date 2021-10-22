package com.example.newyorktimes.di.module

import com.example.newyorktimes.data.network.repository.MostPopularNewsRepository
import org.koin.dsl.module

val repoModule = module {
    single {
        MostPopularNewsRepository(apiHelper = get())
    }
}
