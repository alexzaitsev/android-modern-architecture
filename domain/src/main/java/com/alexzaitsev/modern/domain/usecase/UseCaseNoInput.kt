package com.alexzaitsev.modern.domain.usecase

interface UseCaseNoInput<Output> {

    suspend operator fun invoke(): Output
}
