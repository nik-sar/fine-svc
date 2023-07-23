package ru.rapidbit.finesvc.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import ru.rapidbit.finesvc.serializer.FineCmdDeserializer
import java.util.UUID

@JsonDeserialize(using = FineCmdDeserializer::class)
sealed class FineCmdDto {

    data class GetCaptcha(val userId: UUID) : FineCmdDto();
    data class CheckFine(val userId: UUID, val captcha: String) : FineCmdDto()

}