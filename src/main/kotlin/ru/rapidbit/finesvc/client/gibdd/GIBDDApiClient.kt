package ru.rapidbit.finesvc.client.gibdd

import ru.rapidbit.finesvc.client.gibdd.dto.CaptchaResponse
import ru.rapidbit.finesvc.client.gibdd.dto.CheckFineRequest
import ru.rapidbit.finesvc.client.gibdd.dto.FineResponse

interface GIBDDApiClient {

    fun getCaptcha() : CaptchaResponse
    fun checkFine(request: CheckFineRequest) : FineResponse

}