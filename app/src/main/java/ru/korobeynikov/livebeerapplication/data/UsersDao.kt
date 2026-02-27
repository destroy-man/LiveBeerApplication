package ru.korobeynikov.livebeerapplication.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UsersDao {

    @Query("SELECT * FROM userimpl WHERE phone == :phone")
    suspend fun getUserByPhone(phone: String): UserImpl?

    @Insert
    suspend fun add(user: UserImpl)

    @Delete
    suspend fun delete(user: UserImpl)
}