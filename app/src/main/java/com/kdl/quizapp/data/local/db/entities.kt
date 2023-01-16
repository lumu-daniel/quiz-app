package com.kdl.quizapp.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName ="Question")
data class Question(
    val options: String,
    val correctIndex: Int,
    val questionStr: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)


@Entity(tableName ="CurrentResult")
data class CurrentResult(
    val totalQuestions: Int,
    val correctAnswers: Int,
    val wrongAnswers: Int,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)


@Entity(tableName ="FailedQuestion")
data class FailedQuestion(
    val question: String,
    val wrongAnswer: String,
    val correctAnswer: String,

    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
