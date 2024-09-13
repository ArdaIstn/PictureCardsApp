package com.example.picturecardsapp.ui.fragments


import android.content.Context
import android.content.SharedPreferences
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
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var textToSpeech: TextToSpeech
    private val bundle: RandomWordsDetailFragmentArgs by navArgs()
    private var isEnglish: Boolean = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomWordsDetailBinding.inflate(layoutInflater, container, false)
        sharedPreferences =
            requireContext().getSharedPreferences("learned_words", Context.MODE_PRIVATE)

        textToSpeech = TextToSpeech(requireContext(), this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val wordModel = bundle.wordModel

        with(binding) {
            tvEnglishName.text = wordModel.englishName
            tvTurkishName.text = wordModel.turkishName
            tvEnglishSentence.text = wordModel.englishSentence
            tvTurkishSentence.text = wordModel.turkishSentence
            ivWord.setImageResource(wordModel.image)
        }

        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }


        binding.listenEnglish.setOnClickListener {
            isEnglish = true
            speakOut(wordModel.englishSentence)
        }

        binding.listenTurkish.setOnClickListener {
            isEnglish = false
            speakOut(wordModel.turkishSentence)
        }

        binding.btnLearned.setOnClickListener {
            val sharedPreferences: SharedPreferences =
                requireActivity().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            val gson = Gson()
            val learnedWordsJson = sharedPreferences.getString("learned_words_list", null)
            val learnedWords = if (learnedWordsJson != null) {
                gson.fromJson(learnedWordsJson, Array<WordModel>::class.java).toMutableList()
            } else {
                mutableListOf()
            }

            if (!learnedWords.contains(wordModel)) {
                learnedWords.add(wordModel)
                val updatedLearnedWordsJson = gson.toJson(learnedWords)
                editor.putString("learned_words_list", updatedLearnedWordsJson)
                editor.apply()
            }
            Snackbar.make(
                it,
                "${wordModel.englishName} added to learned words",
                Snackbar.LENGTH_SHORT
            ).show()
            findNavController().navigateUp()
        }
    }


    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech.setLanguage(Locale.US)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "English language is not supported")
            }
        } else {
            Log.e("TTS", "Initialization failed")
        }
    }

    private fun speakOut(text: String) {
        if (isEnglish) {
            textToSpeech.language = Locale.US
        } else {
            textToSpeech.language = Locale("tr", "TR") // Türkçe için dil ayarı
        }
        textToSpeech.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
    }

    override fun onDestroy() {
        if (::textToSpeech.isInitialized) {
            textToSpeech.stop()
            textToSpeech.shutdown()
        }
        super.onDestroy()
    }


}