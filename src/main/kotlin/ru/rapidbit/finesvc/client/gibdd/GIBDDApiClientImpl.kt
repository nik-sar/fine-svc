package ru.rapidbit.finesvc.client.gibdd

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import ru.rapidbit.finesvc.client.gibdd.dto.CaptchaResponse
import ru.rapidbit.finesvc.client.gibdd.dto.CheckFineRequest
import ru.rapidbit.finesvc.client.gibdd.dto.FineResponse

class GIBDDApiClientImpl(private val restTemplate: RestTemplate) : GIBDDApiClient {

    companion object {
        private const val GET_CAPTCHA_URL = "https://check.gibdd.ru/captcha"
        private const val CHECK_FINE_URL = "https://xn--b1afk4ade.xn--90adear.xn--p1ai/proxy/check/fines"
    }

    override fun getCaptcha(): CaptchaResponse {
        return restTemplate.getForEntity(Companion.GET_CAPTCHA_URL, CaptchaResponse::class.java)
            .let {
                it.body ?: throw HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY)
            }
    }

    override fun checkFine(request: CheckFineRequest): FineResponse {
        val headers = HttpHeaders()
            .apply {
                contentType = MediaType.APPLICATION_FORM_URLENCODED
                accept = MutableList(1) {
                    MediaType.APPLICATION_JSON
                }
//                add("Accept", "application/json, text/javascript, */*; q=0.01")
//                add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
            }
        val entity = HttpEntity(request, headers)
        return restTemplate.exchange(Companion.CHECK_FINE_URL, HttpMethod.POST, entity, FineResponse::class.java)
            .let {
                it.body ?: throw HttpClientErrorException(HttpStatus.UNPROCESSABLE_ENTITY)
            }
    }

}