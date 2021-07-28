package com.javiermp.datasources.characters.remote

import com.google.gson.Gson
import com.javiermp.datasources.BuildConfig
import com.javiermp.datasources.characters.remote.MarvelHeroesService.Companion.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Provide "make" methods to create instances of [MarvelHeroesService]
 * and its related dependencies, such as OkHttpClient, Gson, etc.
 */
object MarvelHeroesServiceFactory {

    fun makeMarvelHeroesServiceFactory(isDebug: Boolean, gson: Gson): MarvelHeroesService {
        return makeMarvelHeroesService(makeOkHttpClient(makeLoggingInterceptor(isDebug)), gson)
    }

    private fun makeMarvelHeroesService(okHttpClient: OkHttpClient, gson: Gson): MarvelHeroesService {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(MarvelHeroesService::class.java)
    }

    private fun makeOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(isDebug: Boolean): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = if (isDebug)
            HttpLoggingInterceptor.Level.BODY
        else
            HttpLoggingInterceptor.Level.NONE
        return logging
    }
}