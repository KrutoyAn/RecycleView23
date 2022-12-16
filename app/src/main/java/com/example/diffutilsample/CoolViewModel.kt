package com.example.diffutilsample

import androidx.lifecycle.ViewModel
import com.example.diffutilsample.model.Word

class CoolViewModel : ViewModel() {

    var idCounter = 5
    var newWordListList: MutableList<Word> = mutableListOf(
        Word(1, "Mobiler.dev"),
        Word(2, "Android"),
        Word(3, "Kotlin"),
        Word(4, "RecyclerView"),
    )

    fun createChangedList(list: List<Word>): List<Word> {
        val item = Word(idCounter, "$idCounter элемент")
        idCounter++
        return list.toMutableList()
            .apply {
                add(item)
            }
    }

}