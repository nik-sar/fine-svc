package ru.rapidbit.finesvc.interceptor

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatusCode
import org.springframework.http.client.ClientHttpResponse
import java.io.InputStream

class CustomClientHttpResponse(
    private val headers: HttpHeaders,
    private val body: InputStream,
    private val statusCode: HttpStatusCode,
    private val statusText: String
) : ClientHttpResponse {

    override fun getHeaders() = headers

    override fun getBody() = body

    override fun close() {
        body.close()
    }

    override fun getStatusCode() = statusCode

    @Deprecated(message = "since 6.0")
    override fun getRawStatusCode() = statusCode.value()

    override fun getStatusText() = statusText

}