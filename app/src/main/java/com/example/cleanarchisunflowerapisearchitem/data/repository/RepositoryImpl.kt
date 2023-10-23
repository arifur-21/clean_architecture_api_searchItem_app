package com.example.cleanarchisunflowerapisearchitem.data.repository

import com.example.cleanarchisunflowerapisearchitem.common.Constants
import com.example.cleanarchisunflowerapisearchitem.data.network.ApiService
import com.example.cleanarchisunflowerapisearchitem.domain.model.ImageMode
import com.example.cleanarchisunflowerapisearchitem.domain.repository.ImageRepository
import com.example.cleanarchisunflowerapisearchitem.mapper.toDomain
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ImageRepository {

    override suspend fun getSearchImage(query: String): List<ImageMode> {
        try {
            return apiService.getSearchImage(Constants.KEY,query).hits.map { it.toDomain() }
        }catch (e: HttpException){
            throw Exception(e)
        }
    }
}