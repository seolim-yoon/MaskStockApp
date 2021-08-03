package com.example.maskstockapp.data.model

data class MaskStoreModel(
    val code: String = "",
    val name: String = "",
    val addr: String = "",
    val remainStat: String? = "",
    val lat: Double = 0.0,
    val lng: Double = 0.0,
    var distance: Double = 0.0
)