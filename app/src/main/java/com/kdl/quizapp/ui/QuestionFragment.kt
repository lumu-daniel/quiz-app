package com.kdl.quizapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.kdl.quizapp.R
import kotlinx.android.synthetic.main.fragment_question.*

class QuestionFragment: Fragment(R.layout.fragment_question){

    private lateinit var viewModel: MainViewModel
    private var questionsDone: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.question.observe(viewLifecycleOwner){
            question.text = it.questionStr
            it.options.split("-").let {answers->
                tvOptionOne.text = answers[0]
                tvOptionTwo.text = answers[1]
                tvOptionThree.text = answers[2]
            }
        }

        viewModel.isDone.observe(viewLifecycleOwner){
            questionsDone = it
        }

        btnNext.setOnClickListener {
            if(rbOptionOne.isActivated||rbOptionTwo.isActivated||rbOptionThree.isActivated){
                val selectedAns = when{
                    rbOptionOne.isActivated -> 0
                    rbOptionTwo.isActivated -> 0
                    rbOptionThree.isActivated -> 0
                    else -> -1
                }
                viewModel.question.value?.let {
                    if (it.correctIndex != selectedAns) {
                        viewModel.saveFailedQn(it,selectedAns)
                    }else{
                        viewModel.saveCorrect()
                    }
                }
                if(questionsDone){
                   findNavController().navigate(
                       R.id.action_questionFragment_to_resultFragment
                   )
                }else{
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