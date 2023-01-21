package com.alexzaitsev.modern.data.source.api.model

/**
 * This is a model that should be used only in `api` data source.
 * In may differ from `TestModel` or may contain some additions like @SerializedName for GSON parsing.
 */
internal data class ApiTestModel(
    val testData: String
)
