package com.jeonguk.web.domain.h2

data class Response(
        val firstName: String?,
        val lastName: String?,
        val gender: String?,
        val age: Int?,
        val grid: List<Grid?>?
) {
    data class Grid(
            val streetAddress: String?,
            val city: String?,
            val state: String?,
            val postalCode: String?
    )
}