package edu.uoc.pac2.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.uoc.pac2.R
import kotlinx.android.synthetic.main.activity_book_detail.*

/**
 * An activity representing a single Book detail screen.
 */
class BookDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val itemID = intent.getIntExtra(BookDetailFragment.ARG_ITEM_ID, -1)
            val itemTitle = intent.getStringExtra(ARG_ITEM_TITLE)

            supportActionBar?.title = itemTitle
            val fragment = BookDetailFragment.newInstance(itemID)
            supportFragmentManager.beginTransaction()
                    .add(R.id.frameLayout, fragment)
                    .commit()
        }
    }

    companion object {
        const val ARG_ITEM_TITLE = "itemTitleKey"
    }
}