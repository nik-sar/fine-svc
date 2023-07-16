package ru.rapidbit.finesvc.converter

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.http.HttpInputMessage
import org.springframework.http.HttpOutputMessage
import org.springframework.http.MediaType
import org.springframework.http.converter.HttpMessageConverter
import java.net.URLEncoder
import java.nio.charset.Charset

class ObjectToUrlEncodedMessageConverter<T: Any> constructor(private val objectMapper: ObjectMapper)
    : HttpMessageConverter<T> {

    override fun canRead(clazz: Class<*>, mediaType: MediaType?): Boolean {
        return supportedMediaTypes.contains(mediaType)
    }

    override fun canWrite(clazz: Class<*>, mediaType: MediaType?): Boolean {
        return supportedMediaTypes.contains(mediaType)
    }

    override fun getSupportedMediaTypes(): MutableList<MediaType> {
        return MutableList(1) {
            MediaType.APPLICATION_FORM_URLENCODED
        }
    }

    override fun write(t: T, contentType: MediaType?, outputMessage: HttpOutputMessage) {
        val typeRef = object : TypeReference<Map<String, String>>() {}
        val body = objectMapper.convertValue(t, typeRef)
            .map {
                encodedParam(it)
            }.joinToString("&")
        outputMessage.body.use {
            it.write(body.toByteArray(Charset.defaultCharset()))
        }

    }

    private final fun encodedParam(entry: Map.Entry<String, String>) : String {
        return URLEncoder.encode(entry.key, Charset.defaultCharset()) +
                "=" +
                URLEncoder.encode(entry.value, Charset.defaultCharset())
    }

    override fun read(clazz: Class<out T>, inputMessage: HttpInputMessage): T {
        TODO("Not yet implemented")
    }

}