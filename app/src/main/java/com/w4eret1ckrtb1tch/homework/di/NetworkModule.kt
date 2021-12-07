package com.w4eret1ckrtb1tch.homework.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.w4eret1ckrtb1tch.homework.BuildConfig
import com.w4eret1ckrtb1tch.homework.data.datasource.FocusStartApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesFocusStartApi(retrofit: Retrofit): FocusStartApi =
        retrofit.create(FocusStartApi::class.java)

    @Singleton
    @Provides
    fun providesRetrofit(
        builder: Retrofit.Builder,
        okHttpClient: OkHttpClient,
        gson: Gson
    ): Retrofit {
        return builder
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .baseUrl(FocusStartApi.BASE_URL)
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()

    @Singleton
    @Provides
    fun providesOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(30, TimeUnit.SECONDS)
            .callTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun providesGsonClient(): Gson = GsonBuilder().create()

    @Singleton
    @Provides
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            if (BuildConfig.DEBUG) level = HttpLoggingInterceptor.Level.BASIC
        }
    }
}