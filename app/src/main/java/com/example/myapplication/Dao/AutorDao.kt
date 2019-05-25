package com.example.myapplication.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Entities.Autor

@Dao
interface AutorDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(autor: Autor)

    @Query("SELECT * FROM author_table WHERE idAutor LIKE :id")
    fun getAuthorById(id:Int):Autor

    @Query("SELECT * FROM author_table")
    fun getAllAuthors() : LiveData<List<Autor>>

    @Query("DELETE FROM author_table")
    fun deleteAuthors()

}