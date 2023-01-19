package com.alexzaitsev.modern.data.source.api.api

import com.alexzaitsev.modern.data.source.api.model.ApiTestModel

/**
 * This should be extended to Retrofit interface
 */
interface ApiV1 {

    fun getData(): List<ApiTestModel>
}