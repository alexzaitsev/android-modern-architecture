package com.alexzaitsev.modern.data.source.api

import com.alexzaitsev.modern.data.source.api.api.ApiV1
import com.alexzaitsev.modern.data.source.api.api.ApiV2
import com.alexzaitsev.modern.data.source.api.model.ApiTestModel
import com.github.kittinunf.result.Result
import kotlinx.coroutines.delay
import org.koin.core.annotation.Single

@Single
internal class ApiSource(
    private val apiV1: ApiV1,
    private val apiV2: ApiV2
) {

    /**
     * @return Result. In our sample it's always `success` but in real life you should return
     * `Result.failure` if something went wrong (like server returned HTTP error code or
     * there was a data parsing issue).
     */
    suspend fun getData(): Result<List<ApiTestModel>, Exception> {
        delay(DELAY) // simulate network request

        return Result.success(
            apiV1.getData() +
                apiV2.getData() +
                ApiTestModel(testData = "api0")
        )
    }

    companion object {
        const val DELAY = 1000L
    }
}
