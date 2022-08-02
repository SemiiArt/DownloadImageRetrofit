package com.tuyenhoang.callapiretrofit

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object CommonApp {
    @JvmStatic
    @BindingAdapter(value = ["loadImage"])
    fun loadImage(image:ImageView,imageLink:String?){
        if (imageLink==null){
            image.setBackgroundResource(R.drawable.image2)
        }
        Glide.with(image.context)
            .load(imageLink)
            .error(R.drawable.image2)
            .placeholder(R.drawable.image2)
            .into(image)
    }
}