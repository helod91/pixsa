package com.roche.android.bpi.data.apiservice

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GsonDateDeSerializer : JsonDeserializer<Date> {

    private val format1 = SimpleDateFormat("MMM dd, yyyy 'at' HH:mm z", Locale.getDefault())
    private val format2 = SimpleDateFormat("MMM dd, yyyy HH:mm:ss z", Locale.getDefault())
    private val format3 = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): Date? {
        try {
            val stringDate = json?.asJsonPrimitive?.asString
            return parseDate(stringDate)
        } catch (e: Exception) {
            throw JsonParseException(e.message, e)
        }
    }

    private fun parseDate(dateString: String?): Date? {
        return dateString?.let {
            try {
                format1.parse(it)
            } catch (e: Exception) {
                try {
                    format2.parse(it)
                } catch (e: Exception) {
                    format3.parse(it)
                }
            }
        }
    }
}