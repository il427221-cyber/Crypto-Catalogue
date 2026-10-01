package ru.netology.cryptocatalogue.dto

data class LoadingState (
    val loading: Boolean = false,
    val error: Boolean = false,
    val refreshing: Boolean = false
)