package com.alexzaitsev.modern.view.screen.name1

import android.util.Log
import com.alexzaitsev.modern.domain.model.TestModel
import com.alexzaitsev.modern.domain.usecase.GetDataWithLogicAppliedUseCase
import com.alexzaitsev.modern.view.rule.CoroutineTestRule
import com.alexzaitsev.modern.view.testSideEffect
import com.alexzaitsev.modern.view.testViewState
import com.github.kittinunf.result.failure
import com.github.kittinunf.result.success
import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.equalTo
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class Name1ViewModelTest {

    @get:Rule
    val ruleCoroutine = CoroutineTestRule()

    @RelaxedMockK
    internal lateinit var getDataWithLogicAppliedUseCase: GetDataWithLogicAppliedUseCase

    @Before
    fun before() {
        MockKAnnotations.init(this)
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
    }

    private fun produceSut() = Name1ViewModel(getDataWithLogicAppliedUseCase)

    @Test
    fun `UserAction LoadData updates ViewState if operation succeed`() = runTest {
        coEvery { getDataWithLogicAppliedUseCase.invoke() } returns emptyList<TestModel>().success()
        val sut = produceSut()

        sut.testViewState(skipInitialViewState = true) {
            sut.sendAction(Name1UserAction.LoadData)
            skipItems(1)

            val expected = Name1ViewState(textToDisplay = "", loading = false)
            assertThat(awaitItem(), equalTo(expected))
        }
    }

    @Test
    fun `UserAction LoadData triggers SideEffect GoNext if operation succeed`() = runTest {
        coEvery { getDataWithLogicAppliedUseCase.invoke() } returns emptyList<TestModel>().success()
        val sut = produceSut()

        sut.testSideEffect {
            sut.sendAction(Name1UserAction.LoadData)

            assertThat(awaitItem(), equalTo(Name1SideEffect.GoNext))
        }
    }

    @Test
    fun `UserAction LoadData triggers SideEffect ShowError if operation failed`() = runTest {
        coEvery { getDataWithLogicAppliedUseCase.invoke() } returns Exception("failed").failure()
        val sut = produceSut()

        sut.testSideEffect {
            sut.sendAction(Name1UserAction.LoadData)

            assertThat(awaitItem(), equalTo(Name1SideEffect.ShowError))
        }
    }
}
