package com.techinnovation.nigerianbankcodes.core.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "scan_records")
data class ScanEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val type: String,
    val title: String,
    val sourceLabel: String,
    val extractedText: String,
    val createdAtEpochMs: Long,
    val isFavorite: Boolean = false
)
