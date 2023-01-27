package com.alexzaitsev.modern.domain.usecase

import com.alexzaitsev.modern.data.repository.ModernRepository
import com.alexzaitsev.modern.domain.model.TestModel
import com.alexzaitsev.modern.domain.model.toEntity
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.map
import kotlinx.coroutines.delay
import org.koin.core.annotation.Factory

/**
 * UseCase gets the data and applies a complex logic on it.
 */
@Factory
class GetDataWithLogicAppliedUseCase internal constructor(
    private val repository: ModernRepository
) : UseCaseNoInput<Result<List<TestModel>, Exception>> {

    override suspend operator fun invoke(): Result<List<TestModel>, Exception> {
        val data = repository.getData().map { data -> data.map { it.toEntity() } }
        delay(DELAY) // imagine here we have some complex logic and many lines of code
        // For example here we can join the data from different repositories.
        return data
    }

    companion object {
        const val DELAY = 100L
    }
}
