package com.example.picturecardsapp.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.picturecardsapp.data.model.WordModel
import com.example.picturecardsapp.databinding.ItemLayoutWordsListBinding

class WordsViewHolder(private val binding: ItemLayoutWordsListBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(wordModel: WordModel, onClickListener: (WordModel) -> Unit) {
        binding.tvEnglishWordName.text = wordModel.englishName
        binding.tvTurkishWordName.text = wordModel.turkishName
        binding.ivWord.setImageResource(wordModel.image)

        binding.root.setOnClickListener {
            onClickListener.invoke(wordModel)
        }
    }
}