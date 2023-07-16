package ru.rapidbit.finesvc.client.gibdd.dto

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class CaptchaResponse(

    val token: String,
    val base64jpg: String

)
