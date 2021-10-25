package com.example.newyorktimes.di.module

import android.content.Context
import android.content.SharedPreferences
import com.example.newsdaily.BuildConfig
import com.example.newsdaily.BuildConfig.BASE_URL
import com.example.newyorktimes.data.DataPreference
import com.example.newyorktimes.data.network.api.ApiHelper
import com.example.newyorktimes.data.network.api.ApiService
import com.example.newyorktimes.utlis.NetworkHelper
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module {
    single { provideOkHttpClient() }
    single { provideRetrofit(get()) }
    single { provideApiService(get()) }
    single { provideNetworkHelper(androidContext()) }
    // APIHelperImpl extends APIHelper
    single { ApiHelper(apiService = get()) }

    single { DataPreference(get()) }
}

private fun provideNetworkHelper(context: Context) = NetworkHelper(context)

private fun provideOkHttpClient() =
    if (BuildConfig.DEBUG) {
        // Append api-key parameter to every query
        val apiKeyInterceptor = Interceptor { chain: Interceptor.Chain ->
            var request = chain.request()
            val url = request.url.newBuilder().addQueryParameter("api-key", ApiService.API_KEY).build()
            request = request.newBuilder().url(url).build()
            chain.proceed(request)
        }
        // Will print HTTP requests/responses through LogCat
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(loggingInterceptor)
            .build()
    } else {
        OkHttpClient
            .Builder()
            .build()
    }

private fun provideRetrofit(
    okHttpClient: OkHttpClient
): Retrofit =
    Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .build()

// constructing a service leveragin the end points
private fun provideApiService(retrofit: Retrofit): ApiService =
    retrofit.create(ApiService::class.java)

const val DB_NAME = "NewsDaily.db"