package com.kdl.quizapp.ui

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.kdl.quizapp.R
import kotlinx.android.synthetic.main.fragment_analysis.*

class AnalysisFragment:Fragment(R.layout.fragment_analysis) {

    private lateinit var viewModel: MainViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MainViewModel::class.java]
        viewModel.failedQuestions.observe(viewLifecycleOwner){
            lvAnalysis.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1,it.map {qn->
                "${qn.id}. ${qn.question} \n" +
                        "Your Ans: ${qn.wrongAnswer} \n" +
                        "Correct Ans: ${qn.correctAnswer} \n"
            })
        }
    }
}