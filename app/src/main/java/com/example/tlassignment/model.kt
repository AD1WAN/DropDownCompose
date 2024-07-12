package com.example.tlassignment



data class MediaUpload(
    val inputData: MutableList<InputData>,
    val onUploadDone: (status: String) -> Unit
)

data class InputData(
    val dataName: String,
    var dataValue: String,
    val dataType: Int = InputDataType.dropdown
)

object InputDataType {
    const val editText = 0
    const val dropdown = 1
}
