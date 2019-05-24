package com.example.myapplication.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.myapplication.Entities.Libro

@Dao
interface LibroDao {

    @Insert(onConflict = REPLACE)
    suspend fun insert(libro: Libro)

    //@Query("SELECT * FROM book_table")
    //fun getAllBooks() : LiveData<List<Libro>>

    @Query("DELETE FROM book_table")
    fun deleteBooks()

    @Query("DELETE FROM book_table WHERE isbn Like :isbn")
    fun deleteOneBook(isbn:String)

    @Query("SELECT * FROM book_table WHERE c_nombre LIKE :referencia OR  c_autores LIKE :referencia OR  c_editorial LIKE :referencia OR  c_tag LIKE :referencia")
    fun searchBook(referencia:String) : LiveData<List<Libro>>

    @Query("SELECT a.isbn, a.c_caratula, a.isbn, a.c_nombre,b.nombre,c_editorial FROM book_table a INNER JOIN author_table b WHERE a.c_autores LIKE b.id GROUP BY a.c_nombre")
    fun getAllBooks() : LiveData<List<Libro>>
}