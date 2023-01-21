package com.alexzaitsev.modern.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alexzaitsev.modern.R
import com.alexzaitsev.modern.data.repository.ModernRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val repository by inject<ModernRepository>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CoroutineScope(Dispatchers.IO).launch {
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
