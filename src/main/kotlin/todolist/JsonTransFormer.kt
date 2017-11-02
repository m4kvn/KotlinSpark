package todolist

import com.fasterxml.jackson.databind.ObjectMapper
import spark.ResponseTransformer

class JsonTransFormer(private val objectMapper: ObjectMapper) : ResponseTransformer {

    override fun render(model: Any?): String =
            objectMapper.writeValueAsString(model)
}
