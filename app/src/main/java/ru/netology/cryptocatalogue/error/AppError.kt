package ru.netology.cryptocatalogue.error

sealed class AppError (var code: String): RuntimeException()
class ApiError(val status: Int, code: String): AppError(code)
class NetworkError: AppError("error_network")
class UnknownError: AppError("unknown_error")