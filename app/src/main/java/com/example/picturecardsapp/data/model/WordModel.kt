package com.example.picturecardsapp.data.model

import java.io.Serializable

data class WordModel(
    val id: Int,
    val englishName: String,
    val image: Int,
    val englishSentence:String,
    val turkishSentence:String,
    val turkishName: String,
    var isLearned: Boolean = false
) : Serializable

