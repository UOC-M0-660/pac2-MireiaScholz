package edu.uoc.pac2.ui

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book


/**
 * An activity representing a list of Books.
 */
class BookListActivity : AppCompatActivity() {

    private val TAG = "BookListActivity"
    private lateinit var adapter: BooksListAdapter
    private lateinit var firebaseDB: FirebaseFirestore

    private val books: MutableList<Book> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        firebaseDB = FirebaseFirestore.getInstance()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_list)

        // Init UI
        initToolbar()
        initRecyclerView()

        // Get Books
        getBooks()
    }

    // Init Top Toolbar
    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title
    }

    // Init RecyclerView
    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.book_list)
        // Set Layout Manager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        // Init Adapter
        adapter = BooksListAdapter(books)
        recyclerView.adapter = adapter
    }

    // Fetch books information from firebase
    private fun getBooks() {
        loadBooksFromLocalDb()
        if ((application as MyApplication).hasInternetConnection()) {
            firebaseDB.collection("books")
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        val fetchedBooks: List<Book> = querySnapshot.documents.mapNotNull { it.toObject(Book::class.java) }
                        books.addAll(fetchedBooks)
                        adapter.notifyDataSetChanged()
                        AsyncTask.execute {
                            saveBooksToLocalDatabase(books)
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w(TAG, "Error getting documents: ", exception)
                    }
        }
    }

    private fun loadBooksFromLocalDb() {
        AsyncTask.execute {
            val localBooks: List<Book> = (application as MyApplication).getBooksInteractor().getAllBooks()
            books.addAll(localBooks)
            adapter.notifyDataSetChanged()
        }
    }

    private fun saveBooksToLocalDatabase(books: List<Book>) {
        (application as MyApplication).getBooksInteractor().saveBooks(books)
    }
}