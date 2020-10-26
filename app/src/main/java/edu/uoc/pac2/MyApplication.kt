package edu.uoc.pac2

import android.app.Application
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
        // TODO: Add Internet Check logic.
        return true
    }
}