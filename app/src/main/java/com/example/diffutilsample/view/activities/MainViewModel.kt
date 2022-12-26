package com.example.diffutilsample.view.activities

import androidx.lifecycle.ViewModel
import com.example.diffutilsample.data.ThumbNailDto
import com.example.diffutilsample.data.getImageUrl
import com.example.diffutilsample.dataview.DataViewModel

data class HeroInfoDto(
    val name: String,
    val description: String,
    val thumbnail: ThumbNailDto,
    )

class MainViewModel: ViewModel() {
    fun HeroInfoDto.toDataViewModel(navigationUrl: String): DataViewModel {
        return DataViewModel(
            title = name,
            imageUrl = thumbnail.getImageUrl(),
            navigationLink = navigationUrl
        )
    }
}