package com.example.picturecardsapp.ui.fragments


import android.content.Context

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.picturecardsapp.data.model.WordModel
import com.example.picturecardsapp.databinding.FragmentRandomWordsDetailBinding
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import java.util.Locale


class RandomWordsDetailFragment : Fragment(), TextToSpeech.OnInitListener {

    private lateinit var binding: FragmentRandomWordsDetailBinding
    private lateinit var textToSpeech: TextToSpeech
    private val bundle: RandomWordsDetailFragmentArgs by navArgs()
    private var isEnglish: Boolean = true
    private lateinit var wordModel: WordModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomWordsDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeTextToSpeech()
        setupUI()
        setupListeners()
    }

    private fun initializeTextToSpeech() {
        textToSpeech = TextToSpeech(requireContext(), this)
    }

    private fun setupUI() {
        wordModel = bundle.wordModel
        with(binding) {
            tvEnglishName.text = wordModel.englishName
            tvTurkishName.text = wordModel.turkishName
            tvEnglishSentence.text = wordModel.englishSentence
            tvTurkishSentence.text = wordModel.turkishSentence
            ivWord.setImageResource(wordModel.image)
        }
    }

    private fun setupListeners() {
        binding.apply {
            btnBack.setOnClickListener { findNavController().navigateUp() }

            listenEnglish.setOnClickListener {
                isEnglish = true
                speakOut(wordModel.englishSentence)
            }

            listenTurkish.setOnClickListener {
                isEnglish = false
                speakOut(wordModel.turkishSentence)
            }

            btnLearned.setOnClickListener {
                markWordAsLearned()
                Snackbar.make(it, "${wordModel.englishName} Learned!", Snackbar.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
        }
    }

    private fun markWordAsLearned() {
        val sharedPreferences = requireActivity().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
        val gson = Gson()
        val learnedWordsJson = sharedPreferences.getString("learned_words_list", null)
        val learnedWords = learnedWordsJson?.let {
            gson.fromJson(it, Array<WordModel>::class.java).toMutableList()
        } ?: mutableListOf()

        if (!learnedWords.contains(wordModel)) {
            learnedWords.add(wordModel)
            sharedPreferences.edit()
                .putString("learned_words_list", gson.toJson(learnedWords))
                .apply()
        }
    }

    private fun speakOut(text: String) {
        textToSpeech.language = if (isEnglish) Locale.US else Locale("tr", "TR")
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "Language not supported or missing data")
            }
        } else {
            Log.e("TTS", "Initialization failed")
        }
    }

    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }
}