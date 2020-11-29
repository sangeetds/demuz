package com.example.demuz

import androidx.room.*

@Dao
interface QuestionDao {

    @Query("SELECT * FROM question_list")
    fun getAllQuestions(): List<Question>

    @Query("SELECT * FROM question_list WHERE completed == :completed")
    fun getCompletedQuestions(completed: Boolean): List<Question>

    @Query("SELECT * FROM question_list WHERE favorite == :favorite")
    fun getFavoriteQuestions(favorite: Boolean): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuestion(question: Question)

    @Delete
    fun removeQuestion(question: Question)
}