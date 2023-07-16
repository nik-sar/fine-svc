package ru.rapidbit.finesvc.client.gibdd.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class CheckFineRequest(

    @field:JsonProperty("regnum")
    val regnum: String,
    @field:JsonProperty("regreg")
    val regreg: String,
    @field:JsonProperty("stsnum")
    val stsnum: String,
    @field:JsonProperty("captchaWord")
    val captchaWord: String,
    @field:JsonProperty("captchaToken")
    val captchaToken: String,

)
