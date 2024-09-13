package com.example.picturecardsapp.ui.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.picturecardsapp.data.model.WordModel
import com.example.picturecardsapp.databinding.ItemLayoutWordsListBinding

class WordsAdapter(
    var wordList: List<WordModel>, private val onClickListener: (WordModel) -> Unit
) : RecyclerView.Adapter<WordsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordsViewHolder {
        val binding =
            ItemLayoutWordsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordsViewHolder, position: Int) {
        val wordModel = wordList[position]
        holder.bind(wordModel, onClickListener)
    }

    override fun getItemCount(): Int {
        return wordList.size
    }




}