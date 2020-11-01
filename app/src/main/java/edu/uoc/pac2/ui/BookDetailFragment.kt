package edu.uoc.pac2.ui

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import edu.uoc.pac2.MyApplication
import edu.uoc.pac2.R
import edu.uoc.pac2.data.Book
import edu.uoc.pac2.data.BooksInteractor
import kotlinx.android.synthetic.main.fragment_book_detail.*

/**
 * A fragment representing a single Book detail screen.
 * This fragment is contained in a [BookDetailActivity].
 */
class BookDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Get Book for this detail screen
        loadBook()
    }

    private fun loadBook() {
        arguments?.let { arguments ->
            val uid: Int = arguments.getInt(ARG_ITEM_ID)
            val booksInteractor: BooksInteractor = (activity?.application as MyApplication).getBooksInteractor()
            AsyncTask.execute {
                val book: Book? = booksInteractor.getBookById(uid)
                activity?.runOnUiThread {
                    book?.let { initUI(it) }
                }
            }

        }
    }

    private fun initUI(book: Book) {
        bookDetailAuthor.text = book.title
        bookDetailDate.text = book.publicationDate
        bookDetailDescription.text = book.description
        Picasso.get().load(book.urlImage).into(bookCoverImageView)
    }

    // TODO: Share Book Title and Image URL
    private fun shareContent(book: Book) {
        throw NotImplementedError()
    }

    companion object {
        /**
         * The fragment argument representing the item title that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "itemIdKey"

        fun newInstance(itemId: Int): BookDetailFragment {
            val fragment = BookDetailFragment()
            val arguments = Bundle()
            arguments.putInt(ARG_ITEM_ID, itemId)
            fragment.arguments = arguments
            return fragment
        }
    }
}