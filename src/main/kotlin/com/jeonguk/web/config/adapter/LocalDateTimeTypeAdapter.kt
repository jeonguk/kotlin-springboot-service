package com.jeonguk.web.config.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeTypeAdapter: TypeAdapter<LocalDateTime>() {

    override fun write(out: JsonWriter, value: LocalDateTime?) {
        if (value != null)
            out.value(value.format(DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'")))
        else
            out.nullValue()
    }

    override fun read(`in`: JsonReader): LocalDateTime {
        return LocalDateTime.parse(`in`.nextString(),
                DateTimeFormatter.ofPattern("yyyy'-'MM'-'dd'T'HH':'mm':'ss'.'SSS'Z'"))
    }

}