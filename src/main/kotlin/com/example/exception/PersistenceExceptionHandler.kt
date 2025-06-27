package com.example.exception

import com.example.dto.ErrorResponse
import io.micronaut.context.annotation.Replaces
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpResponse
import io.micronaut.http.server.exceptions.ExceptionHandler
import jakarta.inject.Singleton
import jakarta.persistence.PersistenceException
import org.slf4j.LoggerFactory

@Singleton
@Replaces
class PersistenceExceptionHandler :
    ExceptionHandler<PersistenceException, HttpResponse<ErrorResponse>> {

    companion object {
        private val LOG = LoggerFactory.getLogger(PersistenceExceptionHandler::class.java)

        fun isDuplicateEmail(message: String?): Boolean {
            return message?.contains("duplicate key") == true && message.contains("email")
        }
    }

    override fun handle(
        request: HttpRequest<*>,
        exception: PersistenceException
    ): HttpResponse<ErrorResponse> {
        val message = when {
            isDuplicateEmail(exception.message) -> "Email already exists"
            else -> {
                LOG.error("Unhandled DB exception", exception)
                "Database error occurred"
            }
        }

        return HttpResponse.badRequest(ErrorResponse(message, 400))
    }
}

