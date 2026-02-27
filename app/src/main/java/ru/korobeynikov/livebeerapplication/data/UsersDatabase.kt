package ru.korobeynikov.livebeerapplication.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserImpl::class], version = 1)
abstract class UsersDatabase : RoomDatabase() {
    abstract fun usersDao(): UsersDao
}