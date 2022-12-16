package com.example.diffutilsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diffutilsample.adapter.WordAdapter
import com.example.diffutilsample.databinding.ActivityMainBinding
import com.example.diffutilsample.model.Word

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val wordAdapter by lazy { WordAdapter() }
    val wordList: MutableList<Word> = mutableListOf()

    val newWordListList: MutableList<Word> = mutableListOf(
        Word(1, "Mobiler.dev"),
        Word(2, "Android"),
        Word(3, "Kotlin"),
        Word(4, "RecyclerView"),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setAdapter()

        wordAdapter.setData(wordList)
        binding.buttonAdd.setOnClickListener {
            wordAdapter.setData(newWordListList)
        }

    }


    private fun setAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = wordAdapter
        binding.buttonAdd.setOnClickListener{
            wordList.add()
            wordAdapter.notifyItemInserted(wordList.size -1)

        }
    }
}
