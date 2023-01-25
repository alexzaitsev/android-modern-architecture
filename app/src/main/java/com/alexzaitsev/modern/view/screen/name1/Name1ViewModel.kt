package com.alexzaitsev.modern.view.screen.name1

import android.util.Log
import com.alexzaitsev.modern.domain.usecase.GetDataWithLogicAppliedUseCase
import com.alexzaitsev.modern.view.BaseViewModel
import org.koin.android.annotation.KoinViewModel
import org.orbitmvi.orbit.syntax.simple.intent

@KoinViewModel
class Name1ViewModel(
    private val getDataWithLogicAppliedUseCase: GetDataWithLogicAppliedUseCase
) : BaseViewModel<Name1ViewState, Name1SideEffect, Name1UserAction>(
    initialViewState = Name1ViewState(textToDisplay = "Screen 1", loading = false)
) {

    override fun processUserAction(action: Name1UserAction) = when (action) {
        Name1UserAction.Action1 -> action1()
        Name1UserAction.Action2 -> action2()
        Name1UserAction.Action3 -> action3()
        Name1UserAction.Action4 -> action4()
    }

    private fun action1() = intent {
        // read more about intent{} here https://orbit-mvi.org/Core/overview/#core-operators

        // To update state use either updateState or reduceState (the last one allows to access current state)
        reduceState { current ->
            Name1ViewState(
                textToDisplay = current.textToDisplay,
                loading = true
            )
        }

        getDataWithLogicAppliedUseCase().fold({ list ->
            // To update state use either updateState or reduceState (the last one allows to access current state)
            updateState(
                Name1ViewState(
                    textToDisplay = list.joinTo(buffer = StringBuilder(), separator = ", ")
                        .toString(),
                    loading = false
                )
            )

            // With this mechanism you can navigate further, show alerts, snackbar, etc.
            // To pass any information just convert SideEffect1 to data class.
            sendEffect(Name1SideEffect.SideEffect1)
        }, { ex ->
            Log.e("Name1VM", ex.message, ex)
            // With this mechanism you can navigate further, show alerts, snackbar, etc.
            // To pass any information just convert SideEffect2 to data class.
            sendEffect(Name1SideEffect.SideEffect2)
        })
    }

    private fun action2() {
        // TODO call a UseCase here and change ViewState
    }

    private fun action3() {
        // TODO call a UseCase here and change ViewState
    }

    private fun action4() {
        // TODO call a UseCase here and change ViewState
    }
}
