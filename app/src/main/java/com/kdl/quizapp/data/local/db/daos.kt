package com.kdl.quizapp.data.local.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import javax.annotation.Detainted

@Dao
interface QuestionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestion(question: Question)

    @Delete
    suspend fun deleteQuestion(question: Question)

    @Query("SELECT * FROM Question")
    fun observeAllQuestions(): LiveData<List<Question>>
}

@Dao
interface CurrentResultDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrentResult(currentResult: CurrentResult)

    @Query("DELETE FROM CurrentResult")
    suspend fun deleteAllCurrentResult()

    @Query("SELECT * FROM CurrentResult")
    fun observeAllCurrentResults(): LiveData<List<CurrentResult>>
}

@Dao
interface FailedQuestionDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFailedQuestion(failedQuestion: FailedQuestion)

    @Delete
    suspend fun deleteFailedQuestion(failedQuestion: FailedQuestion)

    @Query("SELECT * FROM FailedQuestion")
    fun observeAllFailedQuestions(): LiveData<List<FailedQuestion>>
}
