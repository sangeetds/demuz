package com.example.demuz

import androidx.room.*

@Dao
interface QuestionDao {

    @Query("SELECT * FROM question_list")
    fun getAllQuestions(): List<Question>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addQuestion(question: Question)

    @Delete
    fun removeQuestion(question: Question)
}