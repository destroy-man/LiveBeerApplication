package ru.korobeynikov.livebeerapplication.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserImpl(
    @PrimaryKey
    val phone: String,
    val fullName: String,
    val birthDate: String
)
