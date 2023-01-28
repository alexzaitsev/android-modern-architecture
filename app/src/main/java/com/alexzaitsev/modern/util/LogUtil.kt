package com.alexzaitsev.modern.util

import com.alexzaitsev.modern.view.BaseViewModel
import timber.log.Timber

fun log(tag: String, message: String) {
    // do not use Timber.tag as this is logged as a separate entry in Bugsnag
    Timber.d("tag=$tag, message=$message")
}

fun BaseViewModel<*, *, *>.log(elementName: String, mviElement: Any) {
    // I'm logging only class name as there may be a lot of data inside and it may be private.
    // You may replace Log.d with Timber.d and configure it to log data into Bugsnag or Fabric, etc.
    Timber.d(
        this::class.java.simpleName ?: "ViewModel",
        "$elementName: ${mviElement.shortSubclassName}"
    )
}

@Suppress("SwallowedException")
val Any.shortClassName: String
    get() = try {
        val fullName = this::class.java.name ?: ""
        try {
            fullName.substring(fullName.lastIndexOf(".") + 1, fullName.indexOf("$"))
        } catch (e: Exception) {
            fullName.substring(fullName.lastIndexOf(".") + 1)
        }
    } catch (e: Exception) {
        ""
    }

@Suppress("SwallowedException")
val Any.shortSubclassName: String
    get() = try {
        val fullName = this::class.java.name ?: ""
        try {
            fullName.substring(fullName.lastIndexOf("$") + 1)
        } catch (e: Exception) {
            shortClassName
        }
    } catch (e: Exception) {
        ""
    }
