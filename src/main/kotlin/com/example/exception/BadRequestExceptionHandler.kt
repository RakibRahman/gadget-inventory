package com.example.exception

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton

@Singleton
class BadRequestExceptionHandler :
    ExceptionHandler<BadRequestException, HttpResponse<ApiError>> {

    override fun handle(
        request: HttpRequest<*>,
        exception: BadRequestException
    ): HttpResponse<ApiError> {
        return HttpResponse.badRequest(
            ApiError(
                message = exception.message ?: "Bad request",
                path = request.path
            )
        )
    }
}
