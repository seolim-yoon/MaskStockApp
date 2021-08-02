package com.example.maskstockapp.repository

import com.example.maskstockapp.data.RetrofitBuilder

class MaskRepository {

    fun fetchStoreInfo() = RetrofitBuilder.service.fetchStoreInfo()
}