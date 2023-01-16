package com.kdl.quizapp.di

import android.content.Context
import androidx.room.Room
import com.kdl.quizapp.common.Constants.DATABASE_NAME
import com.kdl.quizapp.data.QuizRepositoryImpl
import com.kdl.quizapp.data.local.db.*
import com.kdl.quizapp.domain.QuizRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideQuizDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context,QuizDatabase::class.java,DATABASE_NAME)
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    @Singleton
    fun provideQuestionDao(db: QuizDatabase) = db.QuestionDao()

    @Provides
    @Singleton
    fun provideCurrentResultDao(db: QuizDatabase) = db.CurrentResultDao()

    @Provides
    @Singleton
    fun provideFailedQuestionsDao(db: QuizDatabase) = db.FailedQuestionDao()

    @Provides
    @Singleton
    fun provideDataService(
        qDao: QuestionDao,
        crDao: CurrentResultDao,
        fqDao: FailedQuestionDao
        ):QuizRepository{
        return QuizRepositoryImpl(qDao,crDao,fqDao)
    }
}