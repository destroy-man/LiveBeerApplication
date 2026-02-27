package ru.korobeynikov.livebeerapplication.di

import android.app.Application
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.korobeynikov.livebeerapplication.data.UsersDatabase
import ru.korobeynikov.livebeerapplication.data.UsersRepositoryImpl
import ru.korobeynikov.livebeerapplication.domain.UserRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface UsersModule {

    @Binds
    fun bindUsersRepository(usersRepositoryImpl: UsersRepositoryImpl): UserRepository

    companion object {

        @Provides
        @Singleton
        fun provideUsersDatabase(application: Application): UsersDatabase {
            return Room.databaseBuilder(
                application.applicationContext,
                UsersDatabase::class.java, "Users"
            ).build()
        }

        @Provides
        fun provideUsersRepositoryImpl(database: UsersDatabase): UsersRepositoryImpl {
            return UsersRepositoryImpl(database)
        }
    }
}