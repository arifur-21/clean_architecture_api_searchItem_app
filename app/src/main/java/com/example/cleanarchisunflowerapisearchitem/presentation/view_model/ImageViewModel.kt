package com.example.cleanarchisunflowerapisearchitem.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchisunflowerapisearchitem.common.Resource
import com.example.cleanarchisunflowerapisearchitem.domain.use_case.GetImageUseCase
import com.example.cleanarchisunflowerapisearchitem.presentation.state.ImageState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(
    private val getImageUseCase: GetImageUseCase): ViewModel() {

    private val _imageList = mutableStateOf(ImageState())
    val stae: State<ImageState> = _imageList

    private val _query = MutableStateFlow("")

    init {
        onSearch("sun flower")
        viewModelScope.launch {
            _query.debounce(1000).collectLatest {
                onSearch(query = it)
            }
        }
    }

    fun updateQuery(str: String){
        _query.value = str
    }

    fun onSearch(query: String){
        getImageUseCase(query).onEach {result->

            when(result){
                is Resource.Error -> {
                    _imageList.value = ImageState(error = result.message.toString() )
                }
                is Resource.Loading -> {
                    _imageList.value = ImageState(isLoading = true)
                }
                is Resource.Success -> {
                    _imageList.value = ImageState(imageList = result.data ?: emptyList() )
                }
            }
        }.launchIn(viewModelScope)
    }
}