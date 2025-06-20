package com.example

import com.example.dto.Message
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.junit5.annotation.MicronautTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

@MicronautTest
class HelloControllerTest(@Client("/") val client: HttpClient){
    @Test
    fun testHello(){
        val request: HttpRequest<Any> = HttpRequest.GET("/hello")
        val response = client.toBlocking().retrieve(request, Message::class.java)
        assertNotNull(response)
        assertEquals("This world shall know Mircronaut", response.message)
    }
}