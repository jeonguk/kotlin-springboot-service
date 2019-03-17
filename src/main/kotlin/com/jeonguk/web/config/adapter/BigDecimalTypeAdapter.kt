package com.jeonguk.web.config.adapter

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.math.BigDecimal

class BigDecimalTypeAdapter: TypeAdapter<BigDecimal>() {

    override fun write(out: JsonWriter, value: BigDecimal?) {
        if (value != null)
            out.value(value.toString())
        else
            out.nullValue()
    }

    override fun read(`in`: JsonReader): BigDecimal {
        return BigDecimal(`in`.nextString())
    }

}