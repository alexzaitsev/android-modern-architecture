package com.alexzaitsev.modern.util

import android.util.Log
import com.alexzaitsev.modern.view.BaseViewModel

fun BaseViewModel<*, *, *>.log(elementName: String, mviElement: Any) {
    // I'm logging only class name as there may be a lot of data inside and it may be private.
    // You may replace Log.d with Timber.d and configure it to log data into Bugsnag or Fabric, etc.
    Log.d(
        this::class.java.simpleName ?: "ViewModel",
        "$elementName: ${mviElement.shortSubclassName}"
    )
}

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
