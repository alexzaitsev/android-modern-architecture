package com.alexzaitsev.modern.view

import androidx.lifecycle.ViewModel
import com.alexzaitsev.modern.util.log
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseViewModel<ViewState : Any, SideEffect : Any, UserAction : Any>(
    initialViewState: ViewState
) : ViewModel(), ContainerHost<ViewState, SideEffect> {

    private val _userActions = Channel<UserAction>(Channel.BUFFERED)

    override val container = container<ViewState, SideEffect>(
        initialState = initialViewState,
        onCreate = {
            intent {
                _userActions.consumeEach { action ->
                    processUserAction(action)
                }
            }

            onCreate()
        }
    )

    protected abstract fun processUserAction(action: UserAction)

    protected open fun onCreate() {}

    fun updateState(state: ViewState) = updateState { state }

    fun updateState(reducer: (ViewState) -> ViewState) = intent {
        reduceState(reducer)
    }

    protected suspend fun SimpleSyntax<ViewState, SideEffect>.reduceState(
        reducer: (ViewState) -> ViewState
    ) = reduce {
        reducer(this.state).also { newState ->
            log(elementName = "ViewState", mviElement = newState)
        }
    }

    protected suspend fun SimpleSyntax<ViewState, SideEffect>.sendEffect(effect: SideEffect) {
        log(elementName = "SideEffect", mviElement = effect)

        postSideEffect(effect)
    }

    fun sendAction(action: UserAction) = intent {
        log(elementName = "UserAction", mviElement = action)

        _userActions.send(action)
    }
}
