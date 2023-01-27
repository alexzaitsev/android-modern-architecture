package com.alexzaitsev.modern.view

import app.cash.turbine.ReceiveTurbine
import app.cash.turbine.test
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerDecorator
import org.orbitmvi.orbit.RealSettings
import org.orbitmvi.orbit.internal.LazyCreateContainerDecorator
import org.orbitmvi.orbit.internal.TestContainerDecorator
import org.orbitmvi.orbit.internal.TestingStrategy
import kotlin.time.Duration.Companion.seconds

/**
 * Timeout per test
 */
private val TEST_TIMEOUT = 5.seconds

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <VS : Any, SE : Any, UA : Any> BaseViewModel<VS, SE, UA>.testViewState(
    skipInitialViewState: Boolean = false,
    dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher(),
    testBlock: suspend ReceiveTurbine<VS>.() -> Unit
) {
    // refer to Orbit MVI documentation for more details
    container.findTestContainer().test(
        null,
        TestingStrategy.Live(
            RealSettings(
                eventLoopDispatcher = dispatcher,
                intentLaunchingDispatcher = dispatcher
            )
        )
    )

    container.stateFlow.test(timeout = TEST_TIMEOUT) {
        container.findOnCreate().invoke(container.stateFlow.value)

        if (skipInitialViewState) {
            skipItems(1)
        }
        testBlock()
        cancelAndIgnoreRemainingEvents()
    }
}

@OptIn(ExperimentalCoroutinesApi::class)
suspend fun <VS : Any, SE : Any, UA : Any> BaseViewModel<VS, SE, UA>.testSideEffect(
    dispatcher: CoroutineDispatcher = UnconfinedTestDispatcher(),
    testBlock: suspend ReceiveTurbine<SE>.() -> Unit
) {
    container.findTestContainer().test(
        null,
        TestingStrategy.Live(
            RealSettings(
                eventLoopDispatcher = dispatcher,
                intentLaunchingDispatcher = dispatcher
            )
        )
    )

    container.sideEffectFlow.test(timeout = TEST_TIMEOUT) {
        container.findOnCreate().invoke(container.stateFlow.value)

        testBlock()
        cancelAndIgnoreRemainingEvents()
    }
}

internal fun <State : Any> Container<State, *>.findTestContainer(): TestContainerDecorator<State, *> {
    return (this as? TestContainerDecorator<State, *>)
        ?: (this as? ContainerDecorator<State, *>)?.actual?.findTestContainer()
        ?: error("No TestContainerDecorator found!")
}

internal fun <State : Any> Container<State, *>.findOnCreate(): (State) -> Unit {
    return (this as? LazyCreateContainerDecorator<State, *>)?.onCreate
        ?: (this as? ContainerDecorator<State, *>)?.actual?.findOnCreate()
        ?: error("No onCreate found!")
}
