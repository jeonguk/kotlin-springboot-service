package com.jeonguk.web.test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.stream.Collectors

data class Person(
        var userId: String,
        var userName: String,
        var userAdress: String
) {
    override fun toString(): String {
        return "userId $userId userName $userName userAddress $userAdress"
    }
}

data class PersonDto(
        var userId: String,
        val userName: String
)

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class StreamMapContainsTest {

    @Test
    fun `ArrayList 에 체크 하려고 하는 Dto 값이 있는지 체크`() {

        val targetArray = arrayListOf(
                Person("id1", "name1", "address1"),
                Person("id2", "name2", "address2"),
                Person("id3", "name3", "address3")
        )

        val checkData = PersonDto("id2", "name2")

        val checkResult = targetArray.stream()
                .map { p -> PersonDto(p.userId, p.userName) }
                .collect(Collectors.toList())
                .contains(checkData)

        assertEquals(true, checkResult)

    }

}