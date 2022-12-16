package com.example.diffutilsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsample.adapter.WordAdapter
import com.example.diffutilsample.databinding.ActivityMainBinding
import com.example.diffutilsample.model.Word

const val LIST_KEY = "KEY_LIST"

class MainActivity : AppCompatActivity() {

    private var newWordListList: MutableList<Word> = mutableListOf(
        Word(1, "Mobiler.dev"),
        Word(2, "Android"),
        Word(3, "Kotlin"),
        Word(4, "RecyclerView"),
    )
    private var idCounter = newWordListList.size + 1
    private lateinit var binding: ActivityMainBinding
    private val wordAdapter by lazy { WordAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setAdapter()
        if (savedInstanceState == null) {
            wordAdapter.setData(newWordListList)
        } else {
            val list: List<Word> = savedInstanceState.getParcelableArrayList(LIST_KEY) ?: listOf()
            idCounter = list.size
            wordAdapter.setData(list)
        }
    }

    private fun setAdapter() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = wordAdapter
        binding.buttonAdd.setOnClickListener {
            val item = Word(idCounter, "$idCounter элемент")
            idCounter++
            val listToUpdate = wordAdapter.wordList.toMutableList()
                .apply { add(item) }
            wordAdapter.setData(listToUpdate)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(LIST_KEY, wordAdapter.wordList as java.io.Serializable)
        super.onSaveInstanceState(outState)
    }
}