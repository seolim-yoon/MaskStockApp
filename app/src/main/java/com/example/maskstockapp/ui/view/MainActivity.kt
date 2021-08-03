package com.example.maskstockapp.ui.view

import android.Manifest
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.booksearchapp.base.BaseActivity
import com.example.maskstockapp.R
import com.example.maskstockapp.databinding.ActivityMainBinding
import com.example.maskstockapp.ui.adapter.MaskStoreListAdapter
import com.example.maskstockapp.ui.viewmodel.MaskViewModel
import com.google.android.gms.location.LocationServices
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission

class MainActivity : BaseActivity<ActivityMainBinding, MaskViewModel>() {
    override val layoutResID: Int = R.layout.activity_main
    override val viewModel: MaskViewModel by viewModels()

    private val adapter by lazy {
        MaskStoreListAdapter()
    }

    private val fusedLocationProviderClient by lazy {
        LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding.viewmodel = viewModel
        checkPermission()
    }

    private fun checkPermission() {
        val permissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                initView()
            }

            override fun onPermissionDenied(deniedPermissions: List<String>) {
            }
        }

        TedPermission.with(this)
            .setPermissionListener(permissionListener)
            .setDeniedMessage("접근 거부하셨습니다.\n[설정] - [권한]에서 권한을 허용해주세요.")
            .setPermissions(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            .check()
    }

    @SuppressLint("MissingPermission")
    private fun initView() {
        viewDataBinding.rvMaskStoreList.adapter = adapter

        viewModel.maskStoreList.observe(this, Observer { stores ->
            supportActionBar?.title = "마스크 재고 있는 곳 : " + stores.size
            adapter.submitList(stores.sortedBy { stores -> stores.distance })
        })

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                location?.let {
                    viewModel.currentLocation.value = location
                    viewModel.fetchStoreInfo()
                }
            }
            .addOnFailureListener { error ->
                Log.e("seolim", "error : $error")
            }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean = when (item.itemId) {
        R.id.action_refresh -> {
            viewModel.fetchStoreInfo()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }
}