package com.techinnovation.nigerianbankcodes.core.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanDao {
    @Query("SELECT * FROM scan_records ORDER BY createdAtEpochMs DESC")
    fun observeHistory(): Flow<List<ScanEntity>>

    @Query("SELECT * FROM scan_records WHERE type = :type ORDER BY createdAtEpochMs DESC")
    fun observeByType(type: String): Flow<List<ScanEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: ScanEntity)

    @Query("DELETE FROM scan_records WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM scan_records")
    suspend fun clearAll()
}
