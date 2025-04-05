package com.example.gym_mattas.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val phone: String,
    val name: String,
    val bmi: Float,
    val bmiCategory: String
)
