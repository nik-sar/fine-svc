package ru.rapidbit.finesvc.service

import ru.rapidbit.finesvc.dto.FineResultDto
import java.util.UUID

interface FineService {

    fun getCaptcha(userId: UUID) : FineResultDto
    fun checkFine(userId: UUID, captcha: String) : FineResultDto

}