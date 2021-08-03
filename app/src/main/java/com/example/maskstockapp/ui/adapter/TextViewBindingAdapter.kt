package com.example.maskstockapp.ui.adapter

import android.graphics.Color
import android.widget.TextView
import androidx.databinding.BindingAdapter
import org.jetbrains.anko.textColor

object TextViewBindingAdapter {
    @BindingAdapter("setRemainStat")
    @JvmStatic
    fun setRemainStat(tvRemainStat: TextView, remainStat: String?) {
        when (remainStat) {
            "plenty" -> {
                tvRemainStat.text = "충분"
                tvRemainStat.textColor = Color.GREEN
            }
            "some" -> {
                tvRemainStat.text = "여유"
                tvRemainStat.textColor = Color.YELLOW
            }
            "few" -> {
                tvRemainStat.text = "매진 임박"
                tvRemainStat.textColor = Color.RED
            }
            "empty" -> {
                tvRemainStat.text = "재고 없음"
                tvRemainStat.textColor = Color.GRAY
            }
            "break" -> {
                tvRemainStat.text = "판매 중지"
                tvRemainStat.textColor = Color.GRAY
            }
            else -> {
                tvRemainStat.text = ""
            }
        }
    }

    @BindingAdapter("setMaskStock")
    @JvmStatic
    fun setMaskStock(tvMaskStock: TextView, stock: String?) {
        when (stock) {
            "plenty" -> {
                tvMaskStock.text = "100개 이상"
                tvMaskStock.textColor = Color.GREEN
            }
            "some" -> {
                tvMaskStock.text = "30개 이상"
                tvMaskStock.textColor = Color.YELLOW
            }
            "few" -> {
                tvMaskStock.text = "2개 이상"
                tvMaskStock.textColor = Color.RED
            }
            "empty" -> {
                tvMaskStock.text = "1개 이하"
                tvMaskStock.textColor = Color.GRAY
            }
            "break" -> {
                tvMaskStock.text = ""
                tvMaskStock.textColor = Color.GRAY
            }
            else -> {
                tvMaskStock.text = ""
            }
        }
    }

    @BindingAdapter("setDistance")
    @JvmStatic
    fun setDistance(tvDistance: TextView, distance: Double) {
        tvDistance.text = String.format("%.1fkm", distance)
    }
}