package com.example.weatherv1.widgets

import androidx.compose.runtime.Composable

sealed class RequestState<out T> {
    data object Idle : RequestState<Nothing>()
    data object Loading : RequestState<Nothing>()
    data class Success<T>(val data: T) : RequestState<T>()
    data class Error(val error: Throwable) : RequestState<Nothing>()

    fun getDataOrNull(): T? =
        try {
            (this as Success).data
        } catch (e: Exception) {
            null
        }

    fun getErrorOrNull(): Throwable? =
        try {
            (this as Error).error
        } catch (e: Exception) {
            null
        }

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