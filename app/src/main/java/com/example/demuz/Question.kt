package com.example.demuz

data class Question(
    val title: String,
    val companies: List<String>,
    val role: String,
    val frequency: Int,
    val topics: List<String>,
    val college: List<String>,
    val trending: Boolean,
    val source: Int
)