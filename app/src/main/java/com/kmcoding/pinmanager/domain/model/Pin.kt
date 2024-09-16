package com.kmcoding.pinmanager.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pins")
data class Pin(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val code: String,
)
