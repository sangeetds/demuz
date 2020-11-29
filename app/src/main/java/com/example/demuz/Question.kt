package com.example.demuz

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_list")
data class Question(

    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "completed") val completed: Boolean = false,
    @ColumnInfo(name = "favorite") val favorite: Boolean = false,

//    @ColumnInfo(name = "companies") val companies: List<String>,
//    @ColumnInfo(name = "role") val role: String,
//    @ColumnInfo(name = "frequency") val frequency: Int,
//    @ColumnInfo(name = "topics") val topics: List<String>,
//    @ColumnInfo(name = "college") val college: List<String>,
//    @ColumnInfo(name = "trending") val trending: Boolean,
) {
    companion object {
        val filters = listOf("Companies", "Role", "Frequency", "Topics", "College")
    }
}