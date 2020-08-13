package com.exail.stackexchangeusers.di

import android.util.Log
import com.exail.stackexchangeusers.BuildConfig
import com.exail.stackexchangeusers.repository.UserApi
import com.exail.stackexchangeusers.repository.UserRepository
import com.exail.stackexchangeusers.repository.UserRepositoryImpl
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideUserApi(okHttpClient: OkHttpClient, gson: Gson) =
        createWebService<UserApi>(okHttpClient, gson, BuildConfig.BASE_URL)

    @Provides
    fun provideUserRepository(userApi: UserApi): UserRepository = UserRepositoryImpl(userApi)


    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().apply {
            when (BuildConfig.DEBUG) {
                true -> this.addInterceptor(getHttpLoggingInterceptor())
            }
            this.addInterceptor { chain ->
                val requestBuilder = chain.request().newBuilder()
                requestBuilder.header("Content-Type", "application/json")
                return@addInterceptor chain.proceed(requestBuilder.build())
            }
        }.build()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().setLenient().create()

    private fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                Log.d("NETWORK", message)
            }
        }).apply { level = HttpLoggingInterceptor.Level.BODY }
    }

    private inline fun <reified T> createWebService(
        okHttpClient: OkHttpClient, gson: Gson, baseUrl: String
    ): T {
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(T::class.java)
    }

}