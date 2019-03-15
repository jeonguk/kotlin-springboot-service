package com.jeonguk.web.config.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeAdapter: TypeAdapter<LocalTime>() {

    override fun write(out: JsonWriter, value: LocalTime?) {
        if (value != null)
            out.value(value.format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        else
            out.nullValue()
    }

    override fun read(`in`: JsonReader): LocalTime {
        return LocalTime.parse(`in`.nextString(), DateTimeFormatter.ofPattern("HH:mm:ss"))
    }

}