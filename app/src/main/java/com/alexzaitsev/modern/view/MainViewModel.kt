package com.alexzaitsev.modern.view

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class MainViewModel(
    private val getDataWithLogicAppliedUseCase: com.alexzaitsev.modern.domain.usecase.GetDataWithLogicAppliedUseCase
) : ViewModel() {

    fun getData() {
        viewModelScope.launch {
            getDataWithLogicAppliedUseCase().fold({ list ->
                for (element in list) {
                    Log.d("MainActivity", element.testData)
                }
            }, { ex ->
                Log.e("MainActivity", ex.message, ex)
            })
        }
    }
}
