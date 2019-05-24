package com.example.myapplication.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Entities.LibroXTag

@Dao
interface LibroXTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libroXTag: LibroXTag)

}