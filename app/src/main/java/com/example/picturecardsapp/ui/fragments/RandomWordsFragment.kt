package com.example.picturecardsapp.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picturecardsapp.data.model.WordModel
import com.example.picturecardsapp.databinding.FragmentRandomWordsBinding
import com.example.picturecardsapp.ui.adapter.WordsAdapter
import com.example.picturecardsapp.ui.viewmodel.WordsViewModel
import com.google.gson.Gson


class RandomWordsFragment : Fragment() {

    private lateinit var binding: FragmentRandomWordsBinding
    private val viewModel: WordsViewModel by viewModels()
    private lateinit var adapter: WordsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomWordsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = WordsAdapter(viewModel.randomWords.value ?: emptyList()) { wordModel ->
            val action = RandomWordsFragmentDirections.actionRandomWordsToDetail(wordModel)
            findNavController().navigate(action)
        }

        setUpRv()
        observeLiveData()

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshWords()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }


    private fun observeLiveData() {
        viewModel.randomWords.observe(viewLifecycleOwner) { wordList ->
            val sharedPreferences: SharedPreferences =
                requireActivity().getSharedPreferences("learned_words", Context.MODE_PRIVATE)
            val learnedWordsJson = sharedPreferences.getString("learned_words_list", null)

            val learnedWords: List<WordModel> = if (learnedWordsJson != null) {
                Gson().fromJson(learnedWordsJson, Array<WordModel>::class.java).toList()
            } else {
                emptyList()
            }

            val filteredWords = wordList?.filterNot { learnedWords.contains(it) } ?: emptyList()

            adapter.wordList = filteredWords
            adapter.notifyDataSetChanged()
        }
    }


    private fun setUpRv() {
        binding.rvWordsList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvWordsList.adapter = adapter
    }


}