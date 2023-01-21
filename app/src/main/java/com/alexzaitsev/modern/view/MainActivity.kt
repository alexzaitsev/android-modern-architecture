package com.alexzaitsev.modern.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alexzaitsev.modern.R
import com.alexzaitsev.modern.view.home.HomeViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val homeViewModel by inject<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        homeViewModel.getData()
    }
}
