package com.example.myapplication.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.Database.LibroDao
import com.example.myapplication.Entities.Libro

class BookRepository(private val libroDao : LibroDao) {


    //LIBRO DAO METODOS
    fun allBooks() : LiveData<List<Libro>> = libroDao.getAllBooks()

    @WorkerThread
    suspend fun insertBook(libro : Libro){
        libroDao.insertBook(libro)
    }

    fun deleteLibro() = libroDao.deleteLibro()

    @WorkerThread
    fun buscarLibro(referencia : String)=  libroDao.buscarLibro(referencia)

    @WorkerThread
    fun buscarUnLibro(isbn : String)= libroDao.buscarLibro(isbn)
    //----------------------------------------------------------------------

}