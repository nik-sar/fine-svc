package ru.rapidbit.finesvc.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import ru.rapidbit.finesvc.client.gibdd.dto.CheckFineRequest
import ru.rapidbit.finesvc.converter.ObjectToUrlEncodedMessageConverter
import ru.rapidbit.finesvc.interceptor.RestTemplateRequestInterceptor

@EnableMongoRepositories
@Configuration
class AppConfiguration {

    @Bean
    fun restTemplate(objectMapper: ObjectMapper) : RestTemplate {
        val objectToUrlEncodedMessageConverter = ObjectToUrlEncodedMessageConverter<CheckFineRequest>(objectMapper)
        val jsonConverter = MappingJackson2HttpMessageConverter()
        return RestTemplate().apply {
            messageConverters.add(objectToUrlEncodedMessageConverter)
            messageConverters.add(jsonConverter)
            interceptors.add(RestTemplateRequestInterceptor())
        }
    }

}