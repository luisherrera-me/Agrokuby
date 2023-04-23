package com.kubyapp.agrokuby.presentation.home_screen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.kubyapp.agrokuby.presentation.home_screen.components.BrightnessDataHolder

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val lightness = viewModel.getLightness.collectAsState()
    Log.d("TAG", "Lightness state: ${lightness.value.lightNess}")


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
          LazyColumn{
              items(lightness.value.lightNess!!){
                  BrightnessDataHolder(lightNess = it )
              }
          }
        


    }

}