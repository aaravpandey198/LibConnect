package com.example.libconnect.ui.screens

data class LastReadBook(
    val title: String,
    val author: String,
    val format: String,
    val totalPages: Int,
    val progressPercent: Int,
    val chapter: String,
    val tags: List<String>
)

data class FeedItem(
    val initials: String,
    val name: String,
    val text: String,
    val timeAgo: String
)

val sampleBook = LastReadBook(
    title           = "The Design of Everyday Things",
    author          = "Don Norman",
    format          = "PDF",
    totalPages      = 368,
    progressPercent = 67,
    chapter         = "Chapter 5",
    tags            = listOf("Design", "UX", "Non-fiction")
)

val sampleFeed = listOf(
    FeedItem("SR", "Sophia R.",  "Just finished Atomic Habits — truly changed how I think about routines", "2m"),
    FeedItem("MK", "Mihir K.",   "Added 3 new books to my reading list this week — anyone read Klara?",   "18m"),
    FeedItem("PD", "Priya D.",   "Library just uploaded 40 new titles in Bengali literature!",             "1h")
)