package ru.rapidbit.finesvc.interceptor

import org.slf4j.LoggerFactory
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import java.io.ByteArrayInputStream
import java.nio.charset.Charset

class RestTemplateRequestInterceptor : ClientHttpRequestInterceptor {

    companion object {
        private val log = LoggerFactory.getLogger(RestTemplateRequestInterceptor::class.java)
    }

    override fun intercept(
        request: HttpRequest,
        body: ByteArray,
        execution: ClientHttpRequestExecution
    ): ClientHttpResponse {
        log.info("Request: ${request.method}  ${request.uri}")
        log.info("body: " +  body.toString(Charset.defaultCharset()))
        val response = execution.execute(request, body)
        log.info("Response: ${response.statusCode} ${response.statusText}")
        log.info("Body: ")
        val responseBody = response.body.use {
            it.readAllBytes()
        }
        log.info(responseBody.toString(Charset.defaultCharset()))
        return CustomClientHttpResponse(response.headers,
            ByteArrayInputStream(responseBody),
            response.statusCode,
            response.statusText)
    }

}