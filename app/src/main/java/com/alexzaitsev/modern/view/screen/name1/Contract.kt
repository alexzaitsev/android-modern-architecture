package com.alexzaitsev.modern.view.screen.name1

data class Name1ViewState(
    val textToDisplay: String,
    val loading: Boolean
)

sealed class Name1SideEffect {
    object GoNext : Name1SideEffect()
    object ShowError : Name1SideEffect()
}

sealed class Name1UserAction {
    object LoadData : Name1UserAction()
    object Action2 : Name1UserAction()
    object Action3 : Name1UserAction()
    object Action4 : Name1UserAction()
}
