package com.example.myapplication.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.Dao.*
import com.example.myapplication.Entities.*
import com.example.myapplication.models.UnLibro

class BibliotecaRepository(private val autorDao : AutorDao,
                           private val libroDao : LibroDao,
                           private val tagDao: TagDao,
                           private val libroXTagDao: LibroXTagDao){

    //LIBRO DAO METODOS
    fun getAllBooksUnLibro() : LiveData<List<UnLibro>> = libroDao.getAllBooksUnLibro()

    fun getAllBooks() : LiveData<List<Libro>> = libroDao.getAllBooks()



    @WorkerThread
    suspend fun insertBook(libro : Libro){
        libroDao.insert(libro)
    }
    fun getOneBook(isbn:String): LiveData<List<UnLibro>> = libroDao.getOneBook(isbn)

    fun searchBook(referencia:String): LiveData<List<UnLibro>> = libroDao.searchBook(referencia)

    fun deleteBooks() = libroDao.deleteBooks()

    fun deleteOneBook(isbn:String) = libroDao.deleteOneBook(isbn)

    //@WorkerThread
    //fun buscarLibro(referencia : String)=  libroDao.buscarLibro(referencia)

    //AUTOR DAO METODOS

    fun getAllAuthors() : LiveData<List<Autor>> = autorDao.getAllAuthors()

    @WorkerThread
    suspend fun insertAuthor(autor : Autor){
        autorDao.insert(autor)
    }

    //TAG DAO METODOS
    @WorkerThread
    suspend fun insert(tag: Tag){
        tagDao.insert(tag)
    }

    fun getTagById(id:Int): Tag = tagDao.getTagById(id)

    fun getTags() : LiveData<List<Tag>> = tagDao.getTags()


    //LIBROXTAG DAO METODOS
    @WorkerThread
    suspend fun insertLibroXTag(libroXTag: LibroXTag){
        libroXTagDao.insert(libroXTag)
    }
    fun getAllLibroXTag():LiveData<List<LibroXTag>> = libroXTagDao.getAllLibroXTag()

}