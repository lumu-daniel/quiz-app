package com.kdl.quizapp.data

import androidx.lifecycle.LiveData
import com.kdl.quizapp.data.local.db.*
import com.kdl.quizapp.domain.QuizRepository
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    private val questionDao: QuestionDao,
    private val currentResultDao: CurrentResultDao,
    private val failedQuestionDao: FailedQuestionDao
): QuizRepository {
    override suspend fun insertQuestion(question: Question) {
        questionDao.insertQuestion(question)
    }

    override suspend fun deleteQuestion(question: Question) {
        questionDao.deleteQuestion(question)
    }

    override fun observeAllQuestions(): LiveData<List<Question>> {
        return questionDao.observeAllQuestions()
    }

    override suspend fun insertCurrentResult(currentResult: CurrentResult) {
        currentResultDao.insertCurrentResult(currentResult)
    }

    override suspend fun deleteCurrentResult() {
        currentResultDao.deleteAllCurrentResult()
    }

    override fun observeAllCurrentResults(): LiveData<List<CurrentResult>> {
        return currentResultDao.observeAllCurrentResults()
    }

    override suspend fun insertFailedQuestion(failedQuestion: FailedQuestion) {
        failedQuestionDao.insertFailedQuestion(failedQuestion)
    }

    override suspend fun deleteFailedQuestion(failedQuestion: FailedQuestion) {
        failedQuestionDao.deleteFailedQuestion(failedQuestion)
    }

    override fun observeAllFailedQuestions(): LiveData<List<FailedQuestion>> {
        return failedQuestionDao.observeAllFailedQuestions()
    }
}