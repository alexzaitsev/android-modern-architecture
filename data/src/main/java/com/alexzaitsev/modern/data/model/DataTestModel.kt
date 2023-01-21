package com.alexzaitsev.modern.data.model

import com.alexzaitsev.modern.data.source.api.model.ApiTestModel
import com.alexzaitsev.modern.data.source.db.model.DbTestModel

data class DataTestModel(
    val testData: String
)

internal fun ApiTestModel.toData() = DataTestModel(
    testData = testData
)

internal fun DbTestModel.toData() = DataTestModel(
    testData = testData
)
