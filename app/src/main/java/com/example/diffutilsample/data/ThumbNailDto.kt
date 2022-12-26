package com.example.diffutilsample.data

data class ThumbNailDto(
    val extension: String,
    val path: String,
)

fun ThumbNailDto.getImageUrl(): String {
    return "${this.path}.${this.extension}"
}