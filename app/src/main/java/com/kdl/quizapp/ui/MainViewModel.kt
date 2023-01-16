package com.kdl.quizapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kdl.quizapp.common.Constants.androidQuestions
import com.kdl.quizapp.data.local.db.CurrentResult
import com.kdl.quizapp.data.local.db.FailedQuestion
import com.kdl.quizapp.data.local.db.Question
import com.kdl.quizapp.domain.QuizRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val quizRepository: QuizRepository
):ViewModel() {

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question> = _question

    private val _isDone = MutableLiveData<Boolean>()
    val isDone: LiveData<Boolean> = _isDone

    private val _qns: LiveData<List<Question>> = quizRepository.observeAllQuestions()

    val results: LiveData<List<CurrentResult>> = quizRepository.observeAllCurrentResults()

    val failedQuestions = quizRepository.observeAllFailedQuestions()

    private var questionNumber: Int = 0
    private var correctAnswered: Int = 0
    private var wrongAnswered: Int = 0

    init {
        saveQns()
    }

    private fun saveQns() {
        viewModelScope.launch (Dispatchers.IO){
            for (i in androidQuestions){
                quizRepository.insertQuestion(i)
            }
        }

        _question.postValue(_qns.value!![questionNumber])
    }

    fun onNext(){
        _question.postValue(_qns.value?.get(questionNumber))
        if(questionNumber==_qns.value!!.size-1){
            _isDone.postValue(true)
        }else{
            questionNumber++
            _question.postValue(_qns.value!![questionNumber])
        }

        saveResult()
    }

    private fun saveResult() {
        viewModelScope.launch(Dispatchers.IO) {
            quizRepository.deleteCurrentResult()
            quizRepository.insertCurrentResult(
                CurrentResult(
                questionNumber,
                correctAnswered,
                wrongAnswered
            )
            )
        }
    }

    fun onHome(){
        questionNumber = 0
    }

    fun saveCorrect(){
        correctAnswered++
    }

    fun saveFailedQn(qn:Question, selectedOpt:Int){
        wrongAnswered++
        viewModelScope.launch(Dispatchers.IO) {
            quizRepository.insertFailedQuestion(FailedQuestion(
                question = qn.questionStr,
                wrongAnswer = qn.options.split("-")[selectedOpt],
                correctAnswer = qn.options.split("-")[qn.correctIndex]
            ))
        }
    }
}