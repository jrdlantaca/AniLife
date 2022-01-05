package com.example.anilife.databinding

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.example.anilife.vo.AiringAnime
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.*

object MyBindingAdapter {


    @JvmStatic
    @BindingAdapter("bind:animeImage")
    fun loadImage(imageView: ImageView, image: String?) {
        if (!image.isNullOrEmpty()) {
            Glide.with(imageView.context)
                .asBitmap()
                .load(image)
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:resizeImage")
    fun resizeImage(imageView: ImageView, image: String?) {
        if (!image.isNullOrEmpty()) {
            Glide.with(imageView.context)
                .asBitmap()
                .load(image)
                .apply(RequestOptions.overrideOf(225,319))
                .fitCenter()
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("bind:cropImage")
    fun cropImage(imageView: ImageView, image: String?) {
        if (!image.isNullOrEmpty()) {
            Glide.with(imageView.context)
                .asBitmap()
                .load(image)
                .centerCrop()
                .into(imageView)
        }
    }


    @JvmStatic
    @BindingAdapter("bind:animeAiringDate")
    fun convertDate(textView: TextView, airingDate: String?) {
        if (airingDate != null) {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZ", Locale.getDefault())
            val outputFormat = SimpleDateFormat.getDateInstance()
            val date = inputFormat.parse(airingDate)
            val outputDate: String = outputFormat.format(date!!)
            textView.text = outputDate
        } else {
            textView.text = "TBA"
        }
    }


    @JvmStatic
    @BindingAdapter("bind:animeType")
    fun loadAnimeType(textView: TextView, type: String?) {
        if (type != null) {
            if (type == "-") {
                textView.visibility = View.GONE
            } else {
                textView.text = type
            }
        } else {
            textView.visibility = View.GONE
        }
    }
}



