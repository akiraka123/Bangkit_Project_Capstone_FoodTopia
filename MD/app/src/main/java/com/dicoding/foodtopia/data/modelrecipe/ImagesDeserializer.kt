package com.dicoding.foodtopia.data.modelrecipe

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class ImagesDeserializer : JsonDeserializer<Any> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): Any {
        if (json == null) {
            return emptyList<String>()
        }
        return when {
            json.isJsonArray -> {
                json.asJsonArray.map { it.asString }
            }
            json.isJsonPrimitive -> {
                listOf(json.asString)
            }
            else -> {
                emptyList<String>()
            }
        }
    }
}
