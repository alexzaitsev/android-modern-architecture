package com.alexzaitsev.modern.domain.model

import com.alexzaitsev.modern.data.model.DataTestModel

data class TestModel(
    val testData: String
)

internal fun DataTestModel.toEntity() = TestModel(
    testData = testData
)
