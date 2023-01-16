package com.kdl.quizapp.common

import com.kdl.quizapp.data.local.db.Question

object Constants {

    const val DATABASE_NAME = "QuizAppDatabase"

    val androidQuestions = listOf<Question>(
        Question(
            "It is used to create new components.-It is an interface to store global information about an application-All the above",
            2,
            "What is a context in android ?",0),
        Question(
            "Margin is specifying the extra space left on all four sides in layout-Padding is used to offset the content of a view by specific px or dp-All the above",
            2,
            "What is the difference between margin and padding in android layout?",1),
        Question(
            "onPreExecution()-doInBackground()-onPostExecution()",
            2,
            "What are the functionalities in asyncTask in android?",2),
        Question(
            "Interface class.-A class that does not have a name but have functionalities in it-Java class",
            1,
            "What is an anonymous class in android?",3),
//        Question(
//            "It is used to create new components.-It is an interface to store global information about an application-All the above",
//            2,
//            "What is a context in android ?"),
//        Question(
//            "It is used to create new components.-It is an interface to store global information about an application-All the above",
//            2,
//            "What is a context in android ?"),
    )
}