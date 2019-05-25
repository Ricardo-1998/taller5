package com.example.myapplication.Dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.example.myapplication.Entities.Tag

@Dao
interface TagDao{
    @Insert(onConflict = REPLACE)
    suspend fun insert(tag: Tag)

    @Query("SELECT * FROM tag_table WHERE idTag LIKE :id")
    fun getTagById(id:Int): Tag

    @Query("SELECT * FROM tag_table")
    fun getTags() : LiveData<List<Tag>>
}