package com.dicoding.foodtopia.data.modelrecipe

import com.google.gson.JsonElement
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class KeywordsDeserializer : JsonDeserializer<List<String>> {
    override fun deserialize(json: JsonElement, typeOfT: Type, context: JsonDeserializationContext): List<String> {
        return when {
            json.isJsonArray -> {
                context.deserialize(json, List::class.java) as List<String>
            }
            json.isJsonPrimitive -> {
                json.asString.split(",").map { it.trim() }
            }
            else -> {
                throw JsonParseException("Unexpected type for Keywords field")
            }
        }
    }
}
