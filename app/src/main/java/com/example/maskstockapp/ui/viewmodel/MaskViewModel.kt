package com.example.maskstockapp.ui.viewmodel

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.booksearchapp.base.BaseViewModel
import com.example.maskstockapp.data.model.MaskStoreModel
import com.example.maskstockapp.data.response.transformMaskStoreModel
import com.example.maskstockapp.repository.MaskRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MaskViewModel : BaseViewModel() {
    private val repository = MaskRepository()

    var maskStoreList: MutableLiveData<List<MaskStoreModel>> = MutableLiveData()
    var currentLocation: MutableLiveData<Location> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun fetchStoreInfo() {
        showProgressBar()
        addDisposable(repository.fetchStoreInfo(currentLocation.value?.latitude ?: 0.0, currentLocation.value?.latitude ?: 0.0)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ stores ->
                maskStoreList.value =
                        stores.transformMaskStoreModel(currentLocation.value).filterNot { store ->
                            store.remainStat.isNullOrEmpty()
                        }
                hideProgressBar()
            }, { e ->
                Log.e("seolim", "error : " + e.message)
                hideProgressBar()
            })
        )
    }

    private fun showProgressBar() {
        isLoading.value = true
    }

    private fun hideProgressBar() {
        isLoading.value = false
    }
}