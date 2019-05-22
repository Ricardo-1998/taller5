package com.example.myapplication.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

class BibliotecaRepository(private val libroDao : LibroDao, private val autorDao : AutorDao) {


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
    //AUTOR DAO METODOS

    fun getAllAuthors() : LiveData<List<Autor>> = autorDao.getAllAuthors()

    @WorkerThread
    suspend fun insertAuthor(autor : Autor){
        libroDao.insertAuthor(autor)
    }

    fun deleteAuthors() = autorDao.deleteAuthors()
}