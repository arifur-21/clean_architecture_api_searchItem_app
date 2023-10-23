package com.example.cleanarchisunflowerapisearchitem.domain.use_case

import com.example.cleanarchisunflowerapisearchitem.common.Resource
import com.example.cleanarchisunflowerapisearchitem.domain.model.ImageMode
import com.example.cleanarchisunflowerapisearchitem.domain.repository.ImageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class GetImageUseCase @Inject constructor(
    private val repository: ImageRepository
) {

    operator fun invoke(query: String): Flow<Resource<List<ImageMode>>> = flow {

        emit(Resource.Loading())

        try {
            emit(Resource.Success(data = repository.getSearchImage(query = query)))
        }catch (e: HttpException){
            emit(Resource.Error(message = e.toString()))
        }
    }
}