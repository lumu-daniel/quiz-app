package com.kdl.quizapp.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kdl.quizapp.R
import kotlinx.android.synthetic.main.fragment_splash.*

class SplashFragment:Fragment(R.layout.fragment_splash) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnNext.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_questionFragment)
        }
    }
}