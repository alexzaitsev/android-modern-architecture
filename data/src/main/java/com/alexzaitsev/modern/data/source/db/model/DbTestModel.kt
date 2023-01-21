package com.alexzaitsev.modern.data.source.db.model

/**
 * This is a model that should be used only in `db` data source.
 * In may differ from `TestModel` or may contain some additions like @ColumnInfo for Room parsing.
 */
internal data class DbTestModel(
    val testData: String
)
