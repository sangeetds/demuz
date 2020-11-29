package com.example.demuz

class QuestionRepository(private val questionDao: QuestionDao) {
    val allQuestions: List<Question> = questionDao.getAllQuestions()
    val completedQuestions: List<Question> = questionDao.getCompletedQuestions(true)
    val uncompletedQuestions: List<Question> = questionDao.getCompletedQuestions(false)
    val favoriteQuestions: List<Question> = questionDao.getFavoriteQuestions(true)

    fun removeQuestion(question: Question) = questionDao.removeQuestion(question)

    fun addQuestion(question: Question) = questionDao.addQuestion(question)
}