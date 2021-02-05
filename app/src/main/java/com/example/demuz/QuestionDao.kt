package com.example.demuz

import androidx.room.*

@Dao
interface QuestionDao {

    @Query("SELECT * FROM question_list")
    fun getAllQuestions(): List<Song>

    @Query("SELECT * FROM question_list WHERE completed == :completed")
    fun getCompletedQuestions(completed: Boolean): List<Song>

    @Query("SELECT * FROM question_list WHERE favorite == :favorite")
    fun getFavoriteQuestions(favorite: Boolean): List<Song>

    @Query("SELECT * FROM question_list WHERE college LIKE '%' || :colleges || '%'")
    fun filterCollege(colleges: String): List<Song>

    @Query("SELECT * FROM question_list WHERE college LIKE '%' || :companies || '%'")
    fun filterCompanies(companies: String): List<Song>

    @Query("SELECT * FROM question_list WHERE role LIKE '%' || :role || '%'")
    fun filterRole(role: String): List<Song>

    @Query("SELECT * FROM question_list WHERE topics LIKE '%' || :topics || '%'")
    fun filterTopics(topics: String): List<Song>

    @Query("SELECT * FROM question_list WHERE difficulty LIKE '%' || :difficulty || '%'")
    fun filterDifficulty(difficulty: String): List<Song>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuestion(song: Song)

    @Delete
    fun removeQuestion(song: Song)
}