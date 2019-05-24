package com.example.myapplication.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.myapplication.Entities.LibroXAutor

@Dao
interface LibroXAutorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(libroXAutor: LibroXAutor)
}