package com.example.myapplication.ViewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Database.LibraryDatabase
import com.example.myapplication.Entities.*
import com.example.myapplication.Repository.BibliotecaRepository
import com.example.myapplication.models.UnLibro
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BibliotecaViewModel(application: Application) : AndroidViewModel(application) {

    private val bibliotecaRepository: BibliotecaRepository


    init {
        val bookDao = LibraryDatabase.getInstance(application,viewModelScope).libroDao()
        val authorDao = LibraryDatabase.getInstance(application, viewModelScope).autorDao()
        val tagDao = LibraryDatabase.getInstance(application, viewModelScope).tagDao()
        val tagXLibroDao = LibraryDatabase.getInstance(application, viewModelScope).tagXLibroDao()

        bibliotecaRepository = BibliotecaRepository(authorDao,bookDao,tagDao,tagXLibroDao)
    }


    fun getAllBooks() : LiveData<List<Libro>> = bibliotecaRepository.getAllBooks()

    fun searchBook2(referencia:String) : LiveData<List<Libro>> = bibliotecaRepository.searchBook2(referencia)

    fun getAllBooksUnLibro() : LiveData<List<UnLibro>> = bibliotecaRepository.getAllBooksUnLibro()

    fun insertBook(libro : Libro) = viewModelScope.launch(Dispatchers.IO){bibliotecaRepository.insertBook(libro)}

    fun searchBook(referencia:String) : LiveData<List<UnLibro>> = bibliotecaRepository.searchBook(referencia)

    fun deleteBooks() =  viewModelScope.launch(Dispatchers.IO) {bibliotecaRepository.deleteBooks()}

    fun deleteOneBook(isbn:String) = viewModelScope.launch(Dispatchers.IO){bibliotecaRepository.deleteOneBook(isbn)}

    // fun buscarLibro(referencia : String) = viewModelScope.launch(Dispatchers.IO){bibliotecaRepository.buscarLibro(referencia)}




    fun getAllAuthors() : LiveData<List<Autor>> = bibliotecaRepository.getAllAuthors()

    fun insertAuthor(autor: Autor) = viewModelScope.launch(Dispatchers.IO) {bibliotecaRepository.insertAuthor(autor)}

    //fun deleteAuthors() = authorRepository.deleteAuthors()

    fun insertTag(tag: Tag) = viewModelScope.launch(Dispatchers.IO){bibliotecaRepository.insert(tag)}
    fun getTags() : LiveData<List<Tag>> = bibliotecaRepository.getTags()

    fun deleteTags() = bibliotecaRepository.deleteTags()

    fun deleteAutor() = bibliotecaRepository.deleteAutor()

    fun insertLibroXTag(libroXTag: LibroXTag) =
        viewModelScope.launch(Dispatchers.IO){
            bibliotecaRepository.insertLibroXTag(libroXTag)
        }
}