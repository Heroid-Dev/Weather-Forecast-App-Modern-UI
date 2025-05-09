package com.example.weatherv1.widgets

import androidx.compose.runtime.Composable

sealed class RequestState<out T> {
    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Exception) : RequestState<Nothing>()

    fun getDataOrNull(): T? = (this as? Success)?.data
    fun getErrorOrNull(): Exception? = (this as? Error)?.error

    @Composable
    fun DisplayResult(
        onIdle: (@Composable () -> Unit)? = null,
        onSuccess: @Composable () -> Unit,
        onLoading: @Composable () -> Unit,
        onError: @Composable () -> Unit
    ) {
        when (this) {
            is Idle -> onIdle?.invoke()
            is Loading -> onLoading()
            is Success -> onSuccess()
            is Error -> onError()
        }
    }
}