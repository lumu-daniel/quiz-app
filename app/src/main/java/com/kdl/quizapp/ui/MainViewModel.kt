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
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val quizRepository: QuizRepository
):ViewModel() {

    private val _isDone = MutableLiveData<Boolean>()
    val isDone: LiveData<Boolean> = _isDone

    val qns = quizRepository.observeAllQuestions()

    val results: LiveData<List<CurrentResult>> = quizRepository.observeAllCurrentResults()

    val failedQuestions = quizRepository.observeAllFailedQuestions()

    private val _questionNumber = MutableLiveData<Int>()
    val questionNumber: LiveData<Int> = _questionNumber

    private var correctAnswered: Int = 0
    private var wrongAnswered: Int = 0

    init {
        saveQns()
    }

    private fun saveQns() {
        viewModelScope.launch (Dispatchers.IO){
            for (i in androidQuestions){
                try {
                    quizRepository.insertQuestion(i)
                }catch (ex: Exception){
                    ex.printStackTrace()
                }
            }
            delay(100)
            _questionNumber.postValue(0)
        }
    }

    fun onNext(){
        if(_questionNumber.value==qns.value!!.size-1){
            _isDone.postValue(true)
        }else{
            _questionNumber.value = _questionNumber.value!!+1
        }

        saveResult()
    }

    private fun saveResult() {
        viewModelScope.launch(Dispatchers.IO) {
            quizRepository.deleteCurrentResult()
            quizRepository.insertCurrentResult(
                CurrentResult(
                _questionNumber.value!!,
                correctAnswered,
                wrongAnswered
            )
            )
        }
    }

    fun onHome(){
        _questionNumber.postValue(0)
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