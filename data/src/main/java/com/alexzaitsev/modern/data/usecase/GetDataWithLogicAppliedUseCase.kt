package com.alexzaitsev.modern.data.usecase

import com.alexzaitsev.modern.data.model.TestModel
import com.alexzaitsev.modern.data.repository.ModernRepository
import com.github.kittinunf.result.Result
import kotlinx.coroutines.delay
import org.koin.core.annotation.Factory

/**
 * UseCase gets the data and applies a complex logic on it.
 */
@Factory
class GetDataWithLogicAppliedUseCase internal constructor(
    private val repository: ModernRepository
) {

    suspend operator fun invoke(): Result<List<TestModel>, Exception> {
        val data = repository.getData()
        delay(100L) // imagine here we have some complex logic and many lines of code
        // For example here we can join the data from different repositories.
        return data
    }
}
