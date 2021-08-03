package com.example.maskstockapp.repository

import com.example.maskstockapp.data.RetrofitBuilder

class MaskRepository {
    fun fetchStoreInfo(lat: Double, lng: Double) = RetrofitBuilder.service.fetchStoreInfo(lat, lng)
}