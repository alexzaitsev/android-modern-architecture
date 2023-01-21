package com.alexzaitsev.modern.data.source.datastore

import org.koin.core.annotation.Single

@Single
internal class DatastoreSource {

    fun getLastValue() : String = "datastore1"
}
