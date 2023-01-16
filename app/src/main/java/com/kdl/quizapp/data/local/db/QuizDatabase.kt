package com.kdl.quizapp.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [
        Question::class,
        CurrentResult::class,
        FailedQuestion::class],
    version = 4
)
abstract class QuizDatabase:RoomDatabase() {
    abstract fun QuestionDao():QuestionDao
    abstract fun CurrentResultDao():CurrentResultDao
    abstract fun FailedQuestionDao():FailedQuestionDao
}