package com.example.diffutilsample.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsample.databinding.RowItemBinding
import com.example.diffutilsample.model.Word


class WordAdapter : RecyclerView.Adapter<WordAdapter.WordViewHolder>() {
    //1
    private var wordList = emptyList<Word>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        return WordViewHolder(
            RowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        val itemId = wordList[position].id
        holder.binding.textViewWord.text = wordList[position].word
        holder.binding.imageTrash.setOnClickListener {
            if (wordList.isNotEmpty()) {
                setData(wordList.toMutableList().filter { it.id != itemId })
            }
        }
    }

    override fun getItemCount(): Int {
        return wordList.size
    }

    fun setData(newWordList: List<Word>) {
        val diffUtil = WordDiffUtil(wordList, newWordList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        wordList = newWordList as ArrayList<Word>
        diffResults.dispatchUpdatesTo(this)
    }

    //2
    class WordViewHolder(val binding: RowItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}