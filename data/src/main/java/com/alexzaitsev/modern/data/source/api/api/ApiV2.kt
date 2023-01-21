package com.alexzaitsev.modern.data.source.api.api

import com.alexzaitsev.modern.data.source.api.model.ApiTestModel
import org.koin.core.annotation.Single

/**
 * This should be extended to Retrofit interface
 */
internal interface ApiV2 {

    fun getData(): List<ApiTestModel>
}
