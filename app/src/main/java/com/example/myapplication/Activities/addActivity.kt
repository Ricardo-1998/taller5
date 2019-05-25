package com.example.myapplication.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import com.example.myapplication.Entities.Autor
import com.example.myapplication.R
import kotlinx.android.synthetic.main.add_book_activity.*

class addActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_book_activity)
        //Para agregar mas de un Tag
        var textPreTags = ""
        var tags: MutableList<String> = mutableListOf()
        pre_tag.setOnClickListener {
            if (et_tag.text.isNotEmpty()) {

                if (textPreTags.equals("")) {
                    textPreTags = "Tag/s:"
                    tv_id.text = textPreTags + et_tag.text
                    tags.add(et_tag.text.toString())
                    textPreTags = tv_id.text.toString()
                } else {
                    tv_id.text = textPreTags + " , " + et_tag.text
                    textPreTags = tv_id.text.toString()
                    tags.add(et_tag.text.toString())
                }
            }
            et_tag.text.clear()
        }
        //Para agregar mas de un autor
        btn_add_book.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(et_name.text) ||
                TextUtils.isEmpty(et_editorial.text) ||
                TextUtils.isEmpty(et_cartelera.text) ||
                TextUtils.isEmpty(et_author.text) ||
                TextUtils.isEmpty(textPreTags)||
                TextUtils.isEmpty(et_isbn.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            } else {
                var isbn = et_isbn.text.toString()
                var name = et_name.text.toString()
                var editorial = et_editorial.text.toString()
                var cartelera = et_cartelera.text.toString()
                var author = et_author.text.toString()
                var tag = tags.toTypedArray()
                replyIntent.putExtra(EXTRA_ISBN, isbn)
                replyIntent.putExtra(EXTRA_NAME, name)
                replyIntent.putExtra(EXTRA_EDITORIAL, editorial)
                replyIntent.putExtra(EXTRA_CARTELERA, cartelera)
                replyIntent.putExtra(EXTRA_AUTHOR, author)
                replyIntent.putExtra(EXTRA_TAG,tag)

                setResult(Activity.RESULT_OK, replyIntent)
            }
            finish()
        }

    }

    companion object {
        const val EXTRA_NAME = "com.example.myapplication.book.name"
        const val EXTRA_AUTHOR = "com.example.myapplication.book.author"
        const val EXTRA_TAG = "com.example.myapplication.book.tag"
        const val EXTRA_CARTELERA = "com.example.myapplication.book.cartelera"
        const val EXTRA_EDITORIAL = "com.example.myapplication.book.editorial"
        const val EXTRA_ISBN = "com.example.myapplication.book.isbn"
    }

}
