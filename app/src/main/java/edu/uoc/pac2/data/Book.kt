package edu.uoc.pac2.data

/**
 * A book Model representing a piece of content.
 */

data class Book(
        val uid: Int? = null,
        val title: String? = null,
        val author: String? = null,
        val description: String? = null,
        val publicationDate: String? = null,
        val urlImage: String? = null
)