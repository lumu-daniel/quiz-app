package com.kdl.quizapp.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Query("SELECT * FROM questions ORDER BY id DESC")
    fun observeAllQuestions(): LiveData<List<Question>>
}

@Dao
interface CurrentResultDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentResult(currentResult: CurrentResult)

    @Query("DELETE FROM current_result")
    suspend fun deleteAllCurrentResult()

    @Query("SELECT * FROM current_result")
    fun observeAllCurrentResults(): LiveData<List<CurrentResult>>
}

@Dao
interface FailedQuestionDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFailedQuestion(failedQuestion: FailedQuestion)

    @Delete
    suspend fun deleteFailedQuestion(failedQuestion: FailedQuestion)

    @Query("SELECT * FROM failed_question")
    fun observeAllFailedQuestions(): LiveData<List<FailedQuestion>>
}
