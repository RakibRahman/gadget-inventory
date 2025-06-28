package com.example.exception

import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import jakarta.validation.ConstraintViolationException

@Singleton
@Replaces(io.micronaut.validation.exceptions.ConstraintExceptionHandler::class)
class ConstraintViolationException : ExceptionHandler<ConstraintViolationException, HttpResponse<List<ApiError>>> {

    override fun handle(req: HttpRequest<*>, exception: ConstraintViolationException): HttpResponse<List<ApiError>> {
        val errors = exception.constraintViolations.map {
            ApiError(
                message = it.message,
                path = "${req.path}/${it.propertyPath}"
            )
        }
        return HttpResponse.badRequest(errors)
    }
}