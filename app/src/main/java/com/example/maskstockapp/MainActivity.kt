package com.example.maskstockapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.booksearchapp.base.BaseActivity
import com.example.maskstockapp.databinding.ActivityMainBinding
import com.example.maskstockapp.ui.adapter.MaskStoreListAdapter
import com.example.maskstockapp.ui.viewmodel.MaskViewModel

class MainActivity : BaseActivity<ActivityMainBinding, MaskViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: MaskViewModel by viewModels()

    private val adapter by lazy {
        MaskStoreListAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    private fun initView() {
        viewDataBinding.rvMaskStoreList.adapter = adapter

        viewModel.fetchStoreInfo()

        // history insert/delete 후 리스트 변경될 때 마다 history 리스트 갱신
        viewModel.maskStoreList.observe(this, Observer { stores ->
            adapter.submitList(stores.orEmpty())
        })
    }
}