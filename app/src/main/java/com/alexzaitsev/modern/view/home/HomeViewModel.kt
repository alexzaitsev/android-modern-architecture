package com.alexzaitsev.modern.view.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alexzaitsev.modern.data.repository.ModernRepository
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class HomeViewModel(
    private val repository: ModernRepository
) : ViewModel() {

    fun getData() {
        viewModelScope.launch {
            repository.getData().fold({ list ->
                for (element in list) {
                    Log.d("MainActivity", element.testData)
                }
            }, { ex ->
                Log.e("MainActivity", ex.message, ex)
            })
        }
    }
}
