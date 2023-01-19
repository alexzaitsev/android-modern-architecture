package com.alexzaitsev.modern.data.source.db.dao

import com.alexzaitsev.modern.data.source.db.model.DbTestModel

/**
 * This should be extended to Room DAO interface
 */
interface Dao1 {

    fun getData(): List<DbTestModel>
}