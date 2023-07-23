package ru.rapidbit.finesvc.serializer

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.TreeNode
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.deser.std.StdDeserializer
import ru.rapidbit.finesvc.dto.FineCmdDto
import java.util.*

class FineCmdDeserializer : StdDeserializer<FineCmdDto?>(FineCmdDto::class.java) {

    override fun deserialize(p: JsonParser?, ctxt: DeserializationContext?): FineCmdDto? {
        val codec = p?.codec ?: return null
        val node: TreeNode? = codec.readTree(p)
        val userIdNode = node?.get("userId") ?: return null
        val captcha = node.get("captcha")
        val userId = userIdNode.toString()
            .replace("\"", "")
        return if (null != captcha) {
            FineCmdDto.CheckFine(UUID.fromString(userId.toString()), captcha.toString())
        } else {
            FineCmdDto.GetCaptcha(UUID.fromString(userId.toString()))
        }
    }

}