package com.example.myapplication.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Entities.LibroXTag

@Dao
interface LibroXTagDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libroXTag: LibroXTag)

    @Query("SELECT * FROM bookXtag_table")
    fun getAllLibroXTag():LiveData<List<LibroXTag>>

}