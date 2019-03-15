package com.jeonguk.web.config.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.io.IOException
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class LocalTimeAdapter: TypeAdapter<LocalTime>() {

    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: LocalTime?) {
        if (value != null)
            out.value(value.format(DateTimeFormatter.ofPattern("HH:mm:ss")))
        else
            out.nullValue()
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): LocalTime {
        return LocalTime.parse(`in`.nextString(), DateTimeFormatter.ofPattern("HH:mm:ss"))
    }

}