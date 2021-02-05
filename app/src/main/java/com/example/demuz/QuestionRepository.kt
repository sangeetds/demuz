package com.example.demuz

class QuestionRepository(private val questionDao: QuestionDao) {
    val allSongs: List<Song> = questionDao.getAllQuestions()

}