package ru.netology.cryptocatalogue.dto


sealed interface ResultState<out T> {
    data class Success<T>(
        val data: T,
        val fromCache: Boolean = false
    ) : ResultState<T>

    object Failure : ResultState<Nothing>
}