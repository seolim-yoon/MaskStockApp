package com.example.maskstockapp.data

import com.example.maskstockapp.data.response.StoreInfo
import io.reactivex.Single
import retrofit2.http.GET

interface MaskService {
    @GET("sample.json")
    fun fetchStoreInfo(): Single<StoreInfo>

}