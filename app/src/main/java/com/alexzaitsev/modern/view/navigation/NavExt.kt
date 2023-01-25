package com.alexzaitsev.modern.view.navigation

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.spec.Direction

/**
 * Navigates to specified [destination] which may include additional navArgs for target screen
 *
 * @param destination The [Direction] instance to use for navigation
 * @param cleanStack If true navigate back to existing [destination] and reopen screen using [destination]'s navArgs
 * @param popCurrent If true pops up current screen from backstack before navigating to specified [destination]
 */
fun DestinationsNavigator.navigateTo(
    direction: Direction,
    cleanStack: Boolean = false,
    popCurrent: Boolean = false
) {
    if (popCurrent) {
        popBackStack()
    }
    // here you may log to your analytics tool where user was navigated
    navigate(direction = direction, builder = {
        if (cleanStack) {
            this.popUpTo(direction.route, popUpToBuilder = {
                this.inclusive = true
            })
        }
    })
}
