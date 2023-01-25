package com.alexzaitsev.modern.view.screen.name1

data class Name1ViewState(
    val textToDisplay: String,
    val loading: Boolean
)

sealed class Name1SideEffect {
    object SideEffect1 : Name1SideEffect()
    object SideEffect2 : Name1SideEffect()
}

sealed class Name1UserAction {
    object Action1 : Name1UserAction()
    object Action2 : Name1UserAction()
    object Action3 : Name1UserAction()
    object Action4 : Name1UserAction()
}
