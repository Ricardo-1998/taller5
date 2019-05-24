package com.example.myapplication.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.myapplication.Entities.Autor

@Dao
interface AutorDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(autor: Autor)

    @Query("SELECT * FROM author_table")
    fun getAllAuthors() : LiveData<List<Autor>>

}