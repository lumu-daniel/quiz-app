package com.kdl.quizapp.domain

import androidx.lifecycle.LiveData
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

    suspend fun deleteAllFailedQuestions()

    fun observeAllFailedQuestions(): LiveData<List<FailedQuestion>>
}