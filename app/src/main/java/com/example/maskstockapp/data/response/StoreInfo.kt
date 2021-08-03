package com.example.maskstockapp.data.response

import android.location.Location
import com.example.maskstockapp.data.model.MaskStoreModel
import com.example.maskstockapp.util.LocationDistance
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class StoreInfo(
    @Json(name = "count")
    val count: Int,
    @Json(name = "stores")
    val stores: List<Store>
) {
    @JsonClass(generateAdapter = true)
    data class Store(
        @Json(name = "addr")
        val addr: String,
        @Json(name = "code")
        val code: String,
        @Json(name = "created_at")
        val createdAt: String?,
        @Json(name = "lat")
        val lat: Double,
        @Json(name = "lng")
        val lng: Double,
        @Json(name = "name")
        val name: String,
        @Json(name = "remain_stat")
        val remainStat: String?,
        @Json(name = "stock_at")
        val stockAt: String?,
        @Json(name = "type")
        val type: String
    )
}

fun StoreInfo.transformMaskStoreModel(location: Location?) =
    this.stores.map { store ->
        MaskStoreModel(store.code, store.name, store.addr, store.remainStat, store.lat, store.lng,
            LocationDistance.distance(location?.latitude ?: 0.0, location?.longitude ?: 0.0, store.lat, store.lng, "k"))
    }