package com.example.myapplication.Entities


import androidx.room.*
import com.example.myapplication.typeConvert.ListConverter


@Entity(tableName = "book_table"/*,
    foreignKeys = arrayOf(
        ForeignKey(
            entity = Autor::class,
            parentColumns = ["id"],
            childColumns = ["c_autor"],
            onDelete = ForeignKey.CASCADE),
        ForeignKey(
            entity = Tag::class,
            parentColumns = ["id"],
            childColumns = ["c_tag"],
            onDelete = ForeignKey.CASCADE)
        )*/
    )

data class Libro(
    @PrimaryKey val isbn: String,
    @ColumnInfo(name="c_autor")
    var idAutor: Int,
    @ColumnInfo(name="c_editorial")
    val editorial: String,

    @ColumnInfo(name="c_nombre")
    val nombre:String,

    @ColumnInfo(name="c_caratula")
    val caratula:String
)