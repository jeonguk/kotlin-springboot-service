package com.jeonguk.web.test

/**
 * kotlin builder pattern class tset
 */
class KotlinBuilder(
        private val userId: String = "",
        private val userName: String = "",
        private val age: Int?) {

        data class Builder(
            var userId: String = "",
            var userName: String = "",
            var age: Int = 0) {

                fun userId(userId: String) = apply { this.userId = userId }
                fun userName(userName: String) = apply { this.userName = userName }
                fun age(age: Int) = apply { this.age = age }
                fun build() = KotlinBuilder(userId, userName, age)
        }

    override fun toString(): String {
        return "userId = $userId userName = $userName age = $age"
    }
}

// Usages
fun main() {

    val builderTest = KotlinBuilder.Builder()
            .userId("write user id")
            .userName("jeonguk")
            .build()
    println(builderTest)

}