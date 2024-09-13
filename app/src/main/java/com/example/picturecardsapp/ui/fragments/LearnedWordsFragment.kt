package com.example.picturecardsapp.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picturecardsapp.data.model.WordModel
import com.example.picturecardsapp.databinding.FragmentLearnedWordsBinding
import com.example.picturecardsapp.ui.adapter.WordsAdapter
import com.google.gson.Gson


class LearnedWordsFragment : Fragment() {
    private lateinit var binding: FragmentLearnedWordsBinding
    private lateinit var adapter: WordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLearnedWordsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Öğrenilen kelimeleri SharedPreferences'tan al ve adaptöre ata
        val sharedPreferences: SharedPreferences =
            requireActivity().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
        val learnedWordsJson = sharedPreferences.getString("learned_words_list", null)

        val learnedWords: List<WordModel> = if (learnedWordsJson != null) {
            Gson().fromJson(learnedWordsJson, Array<WordModel>::class.java).toList()
        } else {
            emptyList()
        }

        // Adapteri oluştur ve RecyclerView'a ata
        adapter = WordsAdapter(learnedWords) { wordModel ->
            val action = LearnedWordsFragmentDirections.actionLearnedWordsToDetail(wordModel)
            findNavController().navigate(action)
        }

        setUpRecyclerView(adapter)

        if (learnedWords.isEmpty()) {
            binding.tvEmpty.visibility = View.VISIBLE
            binding.lottieAnimationView.visibility = View.VISIBLE


        } else {
            binding.tvEmpty.visibility = View.GONE
            binding.lottieAnimationView.visibility = View.GONE
        }
    }

    private fun setUpRecyclerView(adapter: WordsAdapter) {
        binding.rvLearnedWordList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvLearnedWordList.adapter = adapter
    }
}




