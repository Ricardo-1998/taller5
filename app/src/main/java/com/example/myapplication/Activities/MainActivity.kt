package com.example.myapplication.Activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.myapplication.Adapter.bookAdapter
import com.example.myapplication.Entities.Autor
import com.example.myapplication.Entities.Libro
import com.example.myapplication.Fragment.booksFragment
import com.example.myapplication.R
import com.example.myapplication.ViewModels.BibliotecaViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), booksFragment.clickListener  {

    private val addActivityRequestCode = 1
    lateinit var viewModel: BibliotecaViewModel
    private lateinit var fragment: booksFragment

    override fun itemClick(book: Libro) {
        startActivity(
            Intent(this, BookInfoActivity::class.java)
                .putExtra(addActivity.EXTRA_NAME,book.nombre)
                .putExtra(addActivity.EXTRA_ISBN,book.isbn)
                .putExtra(addActivity.EXTRA_CARTELERA,book.caratula)
                .putExtra(addActivity.EXTRA_EDITORIAL,book.editorial)
                .putExtra(addActivity.EXTRA_AUTHOR,book.autores)
                .putExtra(addActivity.EXTRA_TAG,book.tags))
    }

    override fun delete(book: Libro) {
        viewModel.deleteOneBook(book.isbn)
        Toast.makeText(this,getString(R.string.remove_book),Toast.LENGTH_LONG).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initFragment()
        viewModel = ViewModelProviders.of(this).get(BibliotecaViewModel::class.java)
        viewModel.allBooks().observe(this, Observer { books ->
            // Update the cached copy of the words in the adapter.
            books?.let { fragment.updateAdapter(it)
            }
        })

        btn_refresh.setOnClickListener {
            viewModel.allBooks().observe(this, Observer { books ->
                // Update the cached copy of the words in the adapter.
                books?.let { fragment.updateAdapter(it)
                }
            })
        }

        add_book.setOnClickListener {
            val intent = Intent(this@MainActivity, addActivity::class.java)
            startActivityForResult(intent, addActivityRequestCode)
        }
        delete_book.setOnClickListener {
            viewModel.deleteBooks()
            Toast.makeText(this,getString(R.string.remove_all_books),Toast.LENGTH_LONG).show()
        }
        btn_search.setOnClickListener {
            viewModel.searchBook(et_search.text.toString()).observe(this, Observer { books ->
                // Update the cached copy of the words in the adapter.
                books?.let {
                    fragment.updateAdapter(it)
                }
            })
            et_search.text.clear()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == addActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->

                if(!findAuthor(data.getStringExtra(addActivity.EXTRA_AUTHOR))){
                    var autor = Autor(0,data.getStringExtra(addActivity.EXTRA_AUTHOR))
                    viewModel.insertAuthor(autor)
                }


                val book = Libro(data.getStringExtra(addActivity.EXTRA_ISBN),
                    data.getStringExtra(addActivity.EXTRA_AUTHOR),
                    data.getStringExtra(addActivity.EXTRA_EDITORIAL),
                    data.getStringExtra(addActivity.EXTRA_NAME),
                    data.getStringExtra(addActivity.EXTRA_CARTELERA),
                    data.getStringExtra(addActivity.EXTRA_TAG))//tag.toList())
                viewModel.insertBook(book)

            }
        } else {
            Toast.makeText(applicationContext, R.string.empty_not_saved, Toast.LENGTH_LONG).show()
        }
        viewModel.allBooks().observe(this, Observer { books ->
            // Update the cached copy of the words in the adapter.
            books?.let { fragment.updateAdapter(it)
            }
        })
    }

    private fun initFragment(){
        fragment = booksFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.fragment_content, fragment).commit()
    }

    private fun findAuthor(author:String): Boolean{
        var cont = 0
        if(!viewModel.getAllAuthors().value.isNullOrEmpty()) {
            while (viewModel.getAllAuthors().value!!.size != cont) {
                if (viewModel.getAllAuthors().value!![cont].nombre == author){
                    return true
                }
            }
        }
        return false
    }

}
