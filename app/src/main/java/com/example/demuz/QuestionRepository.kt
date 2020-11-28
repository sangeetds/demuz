package com.example.demuz

class QuestionRepository(private val questionDao: QuestionDao) {
    val allQuestions: List<Question> = questionDao.getAllQuestions()
}