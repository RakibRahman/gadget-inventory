package com.example.exception

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import io.micronaut.serde.annotation.Serdeable
import jakarta.inject.Singleton
import jakarta.validation.ConstraintViolationException

@Singleton
class ValidationExceptionHandler :
    ExceptionHandler<ConstraintViolationException, HttpResponse<ValidationErrorResponse>> {

    override fun handle(
        request: HttpRequest<*>,
        exception: ConstraintViolationException
    ): HttpResponse<ValidationErrorResponse> {

        val errors = exception.constraintViolations.map {
            ApiError(
                message = it.message,
                path = it.propertyPath.toString()
            )
        }

        return HttpResponse.badRequest(
            ValidationErrorResponse(
                message = "Validation failed",
                errors = errors
            )
        )
    }
}

@Serdeable
data class ValidationErrorResponse(
    val message: String,
    val errors: List<ApiError>
)

@Serdeable
data class ApiError(
    val message: String,
    val path: String
)
