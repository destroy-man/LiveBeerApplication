package ru.korobeynikov.livebeerapplication.domain

interface UserRepository {

    suspend fun getUsersByPhone(phone: String): User?

    suspend fun addUser(user: User)

    suspend fun deleteUser(user: User)
}