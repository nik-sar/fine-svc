package ru.rapidbit.finesvc.service

import org.springframework.stereotype.Service
import ru.rapidbit.finesvc.client.gibdd.GIBDDApiClient
import ru.rapidbit.finesvc.client.gibdd.dto.CheckFineRequest
import ru.rapidbit.finesvc.dto.FineResultDto
import java.util.*

@Service
class FineServiceImpl(private val apiClient: GIBDDApiClient) : FineService {

    override fun getCaptcha(userId: UUID): FineResultDto {
        val result = apiClient.getCaptcha()
        TODO("save token to redis")
        return FineResultDto(userId, result.base64jpg)
    }

    override fun checkFine(userId: UUID, captcha: String): FineResultDto {
        TODO("load toke from redis")
        TODO("load driver's data from mongo")
        val request = CheckFineRequest("", "", "", captcha, "")
        val response = apiClient.checkFine(request)
        return FineResultDto(userId, )
    }

}