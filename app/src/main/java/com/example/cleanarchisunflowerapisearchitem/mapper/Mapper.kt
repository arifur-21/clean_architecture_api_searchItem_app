package com.example.cleanarchisunflowerapisearchitem.mapper

import com.example.cleanarchisunflowerapisearchitem.data.dto.HitDto
import com.example.cleanarchisunflowerapisearchitem.domain.model.ImageMode

fun HitDto.toDomain(): ImageMode{
    return ImageMode(
        imageUrl = this.previewURL
    )
}