package ru.rapidbit.finesvc.listener

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ru.rapidbit.finesvc.dto.FineCmdDto
import ru.rapidbit.finesvc.service.FineService

@Component
class CmdListener(
    private val fineService: FineService,
    private val amqpTemplate: AmqpTemplate
) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(CmdListener::class.java)
    }

    @RabbitListener(queues = ["fine-svc"], containerFactory = "simpleContainerFactory")
    fun receivedCmd(data: FineCmdDto) {
        log.info("Received data from queue:")
        log.info(data.toString())

        val fineResult = when(data) {
            is FineCmdDto.GetCaptcha -> {
                fineService.getCaptcha(data.userId)
            }
            is FineCmdDto.CheckFine -> {
                fineService.checkFine(data.userId, data.captcha)
            }
        }
        amqpTemplate.convertAndSend(fineResult)
    }

}