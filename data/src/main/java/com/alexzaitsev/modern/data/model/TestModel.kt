package com.alexzaitsev.modern.data.model

import com.alexzaitsev.modern.data.source.api.model.ApiTestModel
import com.alexzaitsev.modern.data.source.db.model.DbTestModel

data class TestModel(
    val testData: String
)

fun ApiTestModel.toEntity() = TestModel(
    testData = testData
)

fun DbTestModel.toEntity() = TestModel(
    testData = testData
)
