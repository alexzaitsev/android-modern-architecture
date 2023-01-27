package com.alexzaitsev.modern.data.source.datastore

import org.koin.core.annotation.Single

@Single
internal class DatastoreSource {

    @Suppress("FunctionOnlyReturningConstant")
    fun getLastValue(): String = "datastore1"
}
