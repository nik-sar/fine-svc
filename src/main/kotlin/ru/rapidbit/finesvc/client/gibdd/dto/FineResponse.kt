package ru.rapidbit.finesvc.client.gibdd.dto
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Date

@JsonIgnoreProperties(ignoreUnknown = true)
data class FineResponse(

    @field:JsonProperty("durationReg")
    val durationReg: Int?,
    @field:JsonProperty("request")
    val request: String?,
    @field:JsonProperty("code")
    val code: String?,
    @field:JsonProperty("data")
    val data: List<Any>?,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @field:JsonProperty("endDate")
    val endDate: Date?,
    @field:JsonProperty("cafapPicsToken")
    val cafapPicsToken: String?,
    @field:JsonProperty("message")
    val message: String?,
    @field:JsonProperty("duration")
    val duration: Int?,
    @field:JsonProperty("messageReg")
    val messageReg: String?,
    @field:JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @field:JsonProperty("startDate")
    val startDate: Date?,

)
