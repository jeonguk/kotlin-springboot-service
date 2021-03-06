package com.jeonguk.web.config.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class LocalDateTypeAdapter : TypeAdapter<LocalDate>() {

    override fun write(out: JsonWriter, value: LocalDate?) {
        if (value != null)
            out.value(value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
        else
            out.nullValue()
    }

    override fun read(`in`: JsonReader): LocalDate {
        return LocalDate.parse(`in`.nextString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }

}