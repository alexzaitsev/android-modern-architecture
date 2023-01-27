package com.alexzaitsev.modern.data.repository

import com.alexzaitsev.modern.data.model.DataTestModel
import com.alexzaitsev.modern.data.source.api.ApiSource
import com.alexzaitsev.modern.data.source.api.model.ApiTestModel
import com.alexzaitsev.modern.data.source.datastore.DatastoreSource
import com.alexzaitsev.modern.data.source.db.DbSource
import com.alexzaitsev.modern.data.source.db.model.DbTestModel
import com.github.kittinunf.result.success
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class ModernRepositoryTest {

    @RelaxedMockK
    internal lateinit var dbSource: DbSource

    @RelaxedMockK
    internal lateinit var apiSource: ApiSource

    @RelaxedMockK
    internal lateinit var datastoreSource: DatastoreSource

    private val testApiModelList = listOf(
        ApiTestModel("api1"),
        ApiTestModel("api2"),
        ApiTestModel("api3")
    )

    private val testDbModelList = listOf(
        DbTestModel("db1"),
        DbTestModel("db2"),
        DbTestModel("db3")
    )

    @Before
    fun before() {
        MockKAnnotations.init(this)
    }

    private fun produceSut() = ModernRepository(
        dbSource = dbSource,
        apiSource = apiSource,
        datastoreSource = datastoreSource
    )

    @Test
    fun `getData() returns Success if all data sources succeed`() = runBlocking {
        coEvery { apiSource.getData() } returns testApiModelList.success()
        coEvery { dbSource.getData() } returns testDbModelList.success()
        every { datastoreSource.getLastValue() } returns "datastore1"
        val sut = produceSut()

        val result = sut.getData()

        val expected = listOf(
            DataTestModel("api1"),
            DataTestModel("api2"),
            DataTestModel("api3"),
            DataTestModel("db1"),
            DataTestModel("db2"),
            DataTestModel("db3"),
            DataTestModel("datastore1")
        )
        assertEquals(expected.success(), result)
    }
}
