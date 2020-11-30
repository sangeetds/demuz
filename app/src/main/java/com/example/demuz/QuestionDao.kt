package com.example.demuz

import androidx.room.*
import java.nio.file.Files.delete




@Dao
interface QuestionDao {

    @Query("SELECT * FROM question_list")
    fun getAllQuestions(): List<Question>

    @Query("SELECT * FROM question_list WHERE completed == :completed")
    fun getCompletedQuestions(completed: Boolean): List<Question>

    @Query("SELECT * FROM question_list WHERE favorite == :favorite")
    fun getFavoriteQuestions(favorite: Boolean): List<Question>

    @Query("SELECT * FROM question_list WHERE college LIKE '%' || :colleges || '%'")
    fun filterCollege(colleges: String): List<Question>

    @Query("SELECT * FROM question_list WHERE college LIKE '%' || :companies || '%'")
    fun filterCompanies(companies: String): List<Question>

    @Query("SELECT * FROM question_list WHERE role LIKE '%' || :role || '%'")
    fun filterRole(role: String): List<Question>

    @Query("SELECT * FROM question_list WHERE topics LIKE '%' || :topics || '%'")
    fun filterTopics(topics: String): List<Question>

    @Query("SELECT * FROM question_list WHERE difficulty LIKE '%' || :difficulty || '%'")
    fun filterDifficulty(difficulty: String): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuestion(question: Question)

    @Delete
    fun removeQuestion(question: Question)
}