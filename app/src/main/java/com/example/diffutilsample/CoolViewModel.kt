package com.example.diffutilsample

import androidx.lifecycle.ViewModel
import com.example.diffutilsample.model.Word

class CoolViewModel : ViewModel() {

    var idCounter = 5
    var newWordListList: MutableList<Word> = mutableListOf(
        Word(1, "Alfa-Bank"),
        Word(2, "Campus"),
        Word(3, "Android"),
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