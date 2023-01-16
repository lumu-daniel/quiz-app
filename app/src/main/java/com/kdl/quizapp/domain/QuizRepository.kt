package com.kdl.quizapp.domain

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.kdl.quizapp.data.local.db.CurrentResult
import com.kdl.quizapp.data.local.db.FailedQuestion
import com.kdl.quizapp.data.local.db.Question

interface QuizRepository {

    suspend fun insertQuestion(question: Question)

    suspend fun deleteQuestion(question: Question)

    fun observeAllQuestions(): LiveData<List<Question>>

    suspend fun insertCurrentResult(currentResult: CurrentResult)

    suspend fun deleteCurrentResult()

    fun observeAllCurrentResults(): LiveData<List<CurrentResult>>

    suspend fun insertFailedQuestion(failedQuestion: FailedQuestion)

    suspend fun deleteFailedQuestion(failedQuestion: FailedQuestion)

    fun observeAllFailedQuestions(): LiveData<List<FailedQuestion>>
}