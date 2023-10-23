package com.example.cleanarchisunflowerapisearchitem.presentation.screen


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.cleanarchisunflowerapisearchitem.presentation.view_model.ImageViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageScreen(
    viewModel: ImageViewModel = hiltViewModel()
) {

    val result = viewModel.stae.value

    val query = remember {
        mutableStateOf("")
    }

    if (result.isLoading){
        CircularProgressIndicator()
    }
    if (result.error.isNotEmpty()){
        Text(text = "error ${result.error}")
    }

    Box(modifier = Modifier.fillMaxSize()){
        Column {

            TextField(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                value = query.value, onValueChange = {
                query.value = it
                viewModel.updateQuery( query.value)
            }, colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
                leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "")},
                placeholder = { Text(text = "search....")}
                )

            result.imageList?.let {
                LazyColumn{
                    items(it){
                        AsyncImage(model =it.imageUrl , contentDescription ="",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(280.dp)
                                .padding(vertical = 10.dp))
                    }
                }
            }
        }
    }
    


}