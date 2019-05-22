package com.example.myapplication.Repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.myapplication.Database.AutorDao
import com.example.myapplication.Entities.Autor

class AuthorRepository(private val autorDao : AutorDao) {

    //AUTOR DAO METODOS

    fun getAllAuthors() : LiveData<List<Autor>> = autorDao.getAllAuthors()

    @WorkerThread
    suspend fun insertAuthor(autor : Autor){
        autorDao.insertAuthor(autor)
    }

    fun deleteAuthors() = autorDao.deleteAuthors()
}