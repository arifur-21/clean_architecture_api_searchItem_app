package com.example.cleanarchisunflowerapisearchitem.domain.repository

import com.example.cleanarchisunflowerapisearchitem.domain.model.ImageMode

interface ImageRepository {

    suspend fun getSearchImage(query: String): List<ImageMode>
}