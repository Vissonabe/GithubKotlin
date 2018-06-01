package com.example.viswanathankp.github_kotlin.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

class Constants {
    companion object {
        const val STATE_LOADING = 1
        const val STATE_EMPTY_TEXT = 2
        const val STATE_SUCCESS = 3
        const val STATE_FAILURE = 4

        const val HTTPS_API_GITHUB_URL = "https://api.github.com/"
    }
}

