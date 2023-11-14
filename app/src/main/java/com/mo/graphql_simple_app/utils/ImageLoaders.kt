package com.mo.graphql_simple_app.utils

import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.mo.graphql_simple_app.R

fun ImageView.loadImageWithGlide(url: Any?) {
    try {
        if (url == null) {
            Glide.with(this.context)
                .load(R.drawable.ic_flag)
                .centerCrop()
                .into(this)
        } else {
            Glide.with(this.context)
                .load(url)
                .placeholder(R.drawable.ic_flag)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.ic_flag)
                .centerCrop()
                .into(this)
        }

    } catch (e: Exception) {
        Log.e("setImageUsingGlide", e.localizedMessage ?: "Unknown error")
    }
}

