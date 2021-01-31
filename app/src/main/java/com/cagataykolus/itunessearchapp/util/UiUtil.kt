package com.cagataykolus.itunessearchapp.util

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.cagataykolus.itunessearchapp.R
import com.cagataykolus.itunessearchapp.domain.model.Content
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Çağatay Kölüş on 31.01.2020.
 * cagataykolus@gmail.com
 */
class UiUtil {
    companion object {

        /**
         * Helper method for navigating between fragments in NavigationHost.
         */
        fun navigateTo(navControllerView: View, action: Int, bundle: Bundle?) {
            Navigation
                .findNavController(navControllerView)
                .navigate(action, bundle)
        }

        /**
         * Helper method for displaying and loading image to ImageView using Glide.
         * SwipeRefreshLayout CircularProgressDrawable will be used as placeholder while
         * Material Design progress indicator is not yet supported.
         */
        fun displayImage(context: Context, imgUrl: String?, imageView: ImageView) {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.setColorSchemeColors(
                ContextCompat.getColor(context, R.color.colorPrimary),
                ContextCompat.getColor(context, R.color.colorAccent),
                ContextCompat.getColor(context, R.color.colorPrimaryDark))
            circularProgressDrawable.strokeWidth = 10f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            Glide.with(context)
                .asBitmap()
                .load(imgUrl)
                .placeholder(circularProgressDrawable)
                .error(ContextCompat.getDrawable(context, R.drawable.ic_img_broken))
                .fallback(ContextCompat.getDrawable(context, R.drawable.ic_img_fallback))
                .into(imageView)
        }
    }
}