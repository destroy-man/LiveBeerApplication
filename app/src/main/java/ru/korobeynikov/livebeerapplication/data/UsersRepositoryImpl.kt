package ru.korobeynikov.livebeerapplication.data

import ru.korobeynikov.livebeerapplication.domain.User
import ru.korobeynikov.livebeerapplication.domain.UserRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(private val database: UsersDatabase) :
    UserRepository {

    override suspend fun getUsersByPhone(phone: String): User? {
        return database.usersDao().getUserByPhone(phone)?.toUser()
    }

    override suspend fun addUser(user: User) {
        database.usersDao().add(user.toUserImpl())
    }

    override suspend fun deleteUser(user: User) {
        database.usersDao().delete(user.toUserImpl())
    }

    private fun UserImpl.toUser(): User {
        return User(
            phone,
            fullName,
            birthDate
        )
    }

    private fun User.toUserImpl(): UserImpl {
        return UserImpl(
            phone,
            fullName,
            birthDate
        )
    }
}