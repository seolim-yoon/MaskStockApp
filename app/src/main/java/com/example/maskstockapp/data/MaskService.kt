package com.example.maskstockapp.data

import com.example.maskstockapp.data.response.StoreInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MaskService {
    @GET("sample.json/?m=5000")
    fun fetchStoreInfo(
        @Query("lat") lat: Double,
        @Query("lng") lng: Double
    ): Single<StoreInfo>

}