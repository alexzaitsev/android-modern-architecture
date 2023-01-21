package com.alexzaitsev.modern.data

import com.alexzaitsev.modern.data.source.api.api.ApiV1
import com.alexzaitsev.modern.data.source.api.api.ApiV2
import com.alexzaitsev.modern.data.source.api.model.ApiTestModel
import com.alexzaitsev.modern.data.source.db.dao.Dao1
import com.alexzaitsev.modern.data.source.db.dao.Dao2
import com.alexzaitsev.modern.data.source.db.model.DbTestModel
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class DataModule {

    @Single
    internal fun provideApiV1() = object : ApiV1 {
        override fun getData(): List<ApiTestModel> = listOf(
            ApiTestModel(testData = "apiV1_1"),
            ApiTestModel(testData = "apiV1_2"),
            ApiTestModel(testData = "apiV1_3"),
            ApiTestModel(testData = "apiV1_4"),
            ApiTestModel(testData = "apiV1_5")
        )
    }

    @Single
    internal fun provideApiV2() = object : ApiV2 {
        override fun getData(): List<ApiTestModel> = listOf(
            ApiTestModel(testData = "apiV2_1"),
            ApiTestModel(testData = "apiV2_2"),
            ApiTestModel(testData = "apiV2_3"),
            ApiTestModel(testData = "apiV2_4"),
            ApiTestModel(testData = "apiV2_5")
        )
    }

    @Single
    internal fun provideDao1() = object : Dao1 {
        override fun getData(): List<DbTestModel> = listOf(
            DbTestModel(testData = "db_dao1_1"),
            DbTestModel(testData = "db_dao1_2"),
            DbTestModel(testData = "db_dao1_3"),
            DbTestModel(testData = "db_dao1_4"),
            DbTestModel(testData = "db_dao1_5")
        )
    }

    @Single
    internal fun provideDao2() = object : Dao2 {
        override fun getData(): List<DbTestModel> = listOf(
            DbTestModel(testData = "db_dao2_1"),
            DbTestModel(testData = "db_dao2_2"),
            DbTestModel(testData = "db_dao2_3"),
            DbTestModel(testData = "db_dao2_4"),
            DbTestModel(testData = "db_dao2_5")
        )
    }
}
