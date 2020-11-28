package com.example.demuz

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Question::class], version = 1, exportSchema = false)
abstract class QuestionDataBase : RoomDatabase() {

    abstract public fun questionDao(): QuestionDao

    companion object {
        @Volatile private var instance: QuestionDataBase? = null

        @Synchronized
        fun getDatabase(context: Context): QuestionDataBase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuestionDataBase::class.java,
                    "question_list")
                    .allowMainThreadQueries()
                    .build()
            }

            return instance
        }
    }
}