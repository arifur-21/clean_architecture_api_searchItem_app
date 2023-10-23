package com.example.cleanarchisunflowerapisearchitem.di

import com.example.cleanarchisunflowerapisearchitem.common.Constants
import com.example.cleanarchisunflowerapisearchitem.data.network.ApiService
import com.example.cleanarchisunflowerapisearchitem.data.repository.RepositoryImpl
import com.example.cleanarchisunflowerapisearchitem.domain.repository.ImageRepository
import com.example.cleanarchisunflowerapisearchitem.domain.use_case.GetImageUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModul {


    @Provides
    @Singleton
    fun privideApiService(): ApiService{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)

    }

    @Provides
    @Singleton
    fun provideRepository(apiService: ApiService): ImageRepository{
        return RepositoryImpl(apiService)
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: ImageRepository): GetImageUseCase{
        return GetImageUseCase(repository)
    }
}