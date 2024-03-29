package com.alexzaitsev.modern.data.repository

import com.alexzaitsev.modern.data.model.DataTestModel
import com.alexzaitsev.modern.data.model.toData
import com.alexzaitsev.modern.data.source.api.ApiSource
import com.alexzaitsev.modern.data.source.datastore.DatastoreSource
import com.alexzaitsev.modern.data.source.db.DbSource
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.flatMap
import com.github.kittinunf.result.map
import org.koin.core.annotation.Single

@Single
class ModernRepository internal constructor(
    private val apiSource: ApiSource,
    private val dbSource: DbSource,
    private val datastoreSource: DatastoreSource
) {

    /**
     * This method just combines the data from all the data sources.
     * The logic can be much more complex, for example, at first we may check the DB, if there is no
     * item there, we may try to call API.
     *
     * Result library allows to create convenient chains of data.
     *
     * Here we map List<ApiTestModel> to List<TestModel>, then join this data with mapped
     * List<DbTestModel> to List<TestModel>, and then join it with the data from datastore source.
     * In this case, if any of these operations fails, the whole operation fail.
     * You may use `mapError` or `flatMapError` operators to bypass this behavior.
     */
    suspend fun getData(): Result<List<DataTestModel>, Exception> =
        apiSource.getData().map { list -> list.map { it.toData() } }
            .flatMap { fromApi ->
                dbSource.getData().map { list -> list.map { it.toData() } }
                    .map { fromDb -> fromApi + fromDb }
            }.map { fromApiAndDb ->
                fromApiAndDb + DataTestModel(testData = datastoreSource.getLastValue())
            }
}
