package com.pachkhede.assignment4

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "string_methods")
data class StringMethod(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
    val description: String,
    val ex: String

)


