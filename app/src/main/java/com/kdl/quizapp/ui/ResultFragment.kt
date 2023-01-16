package com.kdl.quizapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.kdl.quizapp.R
import kotlinx.android.synthetic.main.fragment_result.*

class ResultFragment:Fragment(R.layout.fragment_result) {

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]

        btnTryAgain.setOnClickListener {
            viewModel.onHome()
            findNavController().navigate(
                R.id.action_resultFragment_to_splashFragment
            )
        }

        btnResultAn.setOnClickListener {
            findNavController().navigate(
                R.id.action_resultFragment_to_analysisFragment
            )
        }

        viewModel.results.observe(viewLifecycleOwner) {
            if(it.isNotEmpty()){
                tvTotalQns.text = "Total Questions: ${it[0].totalQuestions}"
                tvTCorrectAns.text = "Correct Answers (Score): ${it[0].correctAnswers}"
                tvWrongAns.text = "Wrong Answers: ${it[0].wrongAnswers}"
                tvScore.text = "Your Score is: ${it[0].correctAnswers} / ${it[0].totalQuestions}"
            }
        }

    }
}