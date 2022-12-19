package com.example.diffutilsample


import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.diffutilsample.adapter.WordAdapter
import com.example.diffutilsample.databinding.ActivityMainBinding
import com.example.diffutilsample.model.Word

const val LIST_KEY = "KEY_LIST"

class MainActivity : AppCompatActivity() {

    private var newWordListList: MutableList<Word> = mutableListOf(
        Word(1, "Alfa-Bank"),
        Word(2, "Campus"),
        Word(3, "Android"),
        Word(4, "Kotlin"),
        Word(5, "RecyclerView"),
    )
    private var idCounter = newWordListList.size + 1
    private lateinit var imgOne : ImageView
    private lateinit var imgTwo : ImageView
    private lateinit var binding: ActivityMainBinding
    private val wordAdapter by lazy { WordAdapter() }
    private lateinit var  runnable : Runnable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        imgOne = binding.imgAnimation1
        imgTwo = binding.imgAnimation2
        setContentView(binding.root)
        setAdapter()

        if (savedInstanceState == null) {
            wordAdapter.setData(newWordListList)
        } else {
            val list: List<Word> = savedInstanceState.getParcelableArrayList(LIST_KEY) ?: listOf()
            idCounter = list.size
            wordAdapter.setData(list)
        }

        runnable = Runnable  {
            imgOne.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(650)
                .withEndAction {
                    imgOne.scaleX = 0.8f
                    imgOne.scaleY = 0.8f
                    imgOne.alpha = 0.8f
                }

            imgTwo.animate().scaleX(4f).scaleY(4f).alpha(0f).setDuration(1000)
                .withEndAction {
                    imgTwo.scaleX = 0.8f
                    imgTwo.scaleY = 0.8f
                    imgTwo.alpha = 0.8f
                }
        }
    }

    private fun startPulse() {
        runnable.run()
    }

    private fun setAdapter() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = wordAdapter
        binding.button.setOnClickListener {
            startPulse()
            val item = Word(idCounter, "$idCounter элемент")
            idCounter++
            val listToUpdate = wordAdapter.wordList.toMutableList()
                .apply { add(item)
                }

            wordAdapter.setData(listToUpdate)
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable(LIST_KEY, wordAdapter.wordList as java.io.Serializable)
        super.onSaveInstanceState(outState)
    }

}