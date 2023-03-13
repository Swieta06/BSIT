package com.swieta.bsit.di

import androidx.viewbinding.BuildConfig
import com.swieta.bsit.domain.Service
import com.swieta.bsit.domain.RemoteDataSource
import com.swieta.bsit.domain.RemoteDataSourceImpl
import com.swieta.bsit.domain.repository.Repository
import com.swieta.bsit.domain.repository.RepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Singleton
    @Provides
    fun provideOkHttpClient() = if (BuildConfig.DEBUG){
        val loggingIntercepter=HttpLoggingInterceptor()
        loggingIntercepter.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .connectTimeout(10,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .addInterceptor(loggingIntercepter)
            .build()

    }else{
        OkHttpClient
            .Builder()
            .build()

    }
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://private-54eacf-fazztrack.apiary-mock.com/")
        .client(okHttpClient)
        .build()
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit):Service=retrofit.create(Service::class.java)

    @Provides
    @Singleton
    fun provideRepository(remoteDataSource: RemoteDataSource): Repository =
        RepositoryImpl(remoteDataSource)

    @Provides
    @Singleton
    fun provideRemoteDataSource(service: Service): RemoteDataSource =
        RemoteDataSourceImpl(service)
}