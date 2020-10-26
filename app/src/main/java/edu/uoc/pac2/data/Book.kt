package edu.uoc.pac2.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * A book Model representing a piece of content.
 */

@Entity
data class Book(
        @PrimaryKey val uid: Int? = null,
        @ColumnInfo(name = "title") val title: String? = null,
        @ColumnInfo(name = "author") val author: String? = null,
        @ColumnInfo(name = "description") val description: String? = null,
        @ColumnInfo(name = "publicationDate") val publicationDate: String? = null,
        @ColumnInfo(name = "urlImage") val urlImage: String? = null
)