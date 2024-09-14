package com.kmcoding.pinmanager.domain

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pins")
data class Pin(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val code: String,
)
