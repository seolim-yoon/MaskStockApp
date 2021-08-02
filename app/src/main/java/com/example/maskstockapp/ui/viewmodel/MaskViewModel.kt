package com.example.maskstockapp.ui.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.booksearchapp.base.BaseViewModel
import com.example.maskstockapp.data.model.MaskStoreModel
import com.example.maskstockapp.data.response.transformMaskStoreModel
import com.example.maskstockapp.repository.MaskRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MaskViewModel : BaseViewModel() {
    val repository = MaskRepository()

    var maskStoreList: MutableLiveData<List<MaskStoreModel>> = MutableLiveData()

    fun fetchStoreInfo() {
        addDisposable(
            repository.fetchStoreInfo()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ stores ->
                    maskStoreList.value = stores.transformMaskStoreModel()
                    Log.v("seolim", "size : " + stores.transformMaskStoreModel().size)
                }, { e ->
                    Log.e("seolim", "e : " + e.message)
                })
        )
    }

}