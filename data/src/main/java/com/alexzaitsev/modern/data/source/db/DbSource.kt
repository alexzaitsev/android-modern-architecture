package com.alexzaitsev.modern.data.source.db

import com.alexzaitsev.modern.data.source.db.dao.Dao1
import com.alexzaitsev.modern.data.source.db.dao.Dao2
import com.alexzaitsev.modern.data.source.db.model.DbTestModel
import com.github.kittinunf.result.Result

class DbSource(
    private val dao1: Dao1,
    private val dao2: Dao2
) {


    /**
     * @return Result. In our sample it's always `success` but in real life you should return
     * `Result.failure` if something went wrong (like server returned HTTP error code or
     * there was a data parsing issue).
     */
    fun getData(): Result<List<DbTestModel>, Exception> = Result.success(
        dao1.getData() +
                dao2.getData() +
                DbTestModel(testData = "db5")
    )
}