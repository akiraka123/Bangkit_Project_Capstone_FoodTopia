package com.dicoding.foodtopia.data.modelrecipe

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class RecipeIngredientPartsDeserializer : JsonDeserializer<List<String>> {
    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): List<String> {
        return when {
            json?.isJsonArray == true -> {
                json.asJsonArray.map { it.asString }
            }
            json?.isJsonPrimitive == true -> {
                listOf(json.asString)
            }
            else -> emptyList()
        }
    }
}
