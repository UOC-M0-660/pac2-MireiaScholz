package edu.uoc.pac2

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.room.Room
import edu.uoc.pac2.data.ApplicationDatabase
import edu.uoc.pac2.data.BooksInteractor

/**
 * Entry point for the Application.
 */
class MyApplication : Application() {

    private lateinit var booksInteractor: BooksInteractor

    override fun onCreate() {
        super.onCreate()
        val roomDb: ApplicationDatabase = Room.databaseBuilder(
                applicationContext,
                ApplicationDatabase::class.java, "pec2-database"
        ).build()
        booksInteractor = BooksInteractor(roomDb.bookDao())
    }

    fun getBooksInteractor(): BooksInteractor {
        return booksInteractor
    }

    fun hasInternetConnection(): Boolean {
        val cm = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        var result = false
        if (activeNetwork != null) {
            result = activeNetwork.isConnectedOrConnecting
        }
        return result
    }
}