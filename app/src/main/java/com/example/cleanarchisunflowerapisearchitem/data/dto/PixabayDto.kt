package com.example.cleanarchisunflowerapisearchitem.data.dto

data class PixabayDto(
    val hits: List<HitDto>,
    val total: Int,
    val totalHits: Int
)