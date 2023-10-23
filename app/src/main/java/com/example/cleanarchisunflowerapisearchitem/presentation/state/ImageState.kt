package com.example.cleanarchisunflowerapisearchitem.presentation.state

import com.example.cleanarchisunflowerapisearchitem.domain.model.ImageMode

data class ImageState(
    val isLoading: Boolean = false,
    val imageList: List<ImageMode> = emptyList(),
    val error: String = ""
)
