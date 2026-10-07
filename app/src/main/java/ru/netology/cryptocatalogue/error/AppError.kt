package ru.netology.cryptocatalogue.error

sealed class AppError (var code: String)
class ApiError(code: String): AppError(code)
object NetworkError: AppError("error_network")
object UnknownError: AppError("unknown_error")