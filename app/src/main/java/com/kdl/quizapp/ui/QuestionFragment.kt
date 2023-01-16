package com.kdl.quizapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kdl.quizapp.R
import com.kdl.quizapp.data.local.db.Question
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment: Fragment(R.layout.fragment_question){

    private lateinit var viewModel: MainViewModel
    private var questionsDone: Boolean = false
    private var selectedAns = -1
    private var questions:List<Question>? = null
    private var currentQuestion: Question? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        viewModel.qns.observe(viewLifecycleOwner){
            questions = it
        }

        viewModel.questionNumber.observe(viewLifecycleOwner){
            questions?.get(it!!)?.let { qn->
                currentQuestion = qn
                question.text = qn.questionStr
                qn.options.split("-").let {answers->
                    radia_id1.text = answers[0]
                    radia_id2.text = answers[1]
                    radia_id3.text = answers[2]
                }
            }

        }

        viewModel.isDone.observe(viewLifecycleOwner){
            questionsDone = it
        }

        groupradio.setOnCheckedChangeListener{rg, id ->
             selectedAns = when(id){
                R.id.radia_id1 -> 0
                 R.id.radia_id2-> 1
                 R.id.radia_id3 -> 2
                else -> -1
            }

        }

        btnNext.setOnClickListener {
            if(groupradio.checkedRadioButtonId !=-1){
                viewModel.qns.value?.let {
                    currentQuestion?.let {qn->
                        if (qn.correctIndex != selectedAns) {
                            viewModel.saveFailedQn(qn,selectedAns)
                        }else{
                            viewModel.saveCorrect()
                        }
                    }
                }
                if(questionsDone){
                    findNavController().navigate(
                        R.id.action_questionFragment_to_resultFragment
                    )
                }else{
                    groupradio.clearCheck()
                    viewModel.onNext()
                }
            }else{
                Snackbar.make(requireView(),"Please Select Answer", Snackbar.LENGTH_LONG).show()
            }
        }
        btnHome.setOnClickListener {
            viewModel.onHome()
            findNavController().navigate(R.id.action_questionFragment_to_splashFragment)
        }
    }
}