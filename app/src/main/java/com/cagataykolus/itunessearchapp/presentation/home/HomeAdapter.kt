package com.cagataykolus.itunessearchapp.presentation.home

import android.content.Context
import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import com.cagataykolus.itunessearchapp.R
import com.cagataykolus.itunessearchapp.domain.model.Content
import com.cagataykolus.itunessearchapp.util.UiUtil
//import kotlinx.android.synthetic.main.content_list_item.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
class HomeAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Content> = ArrayList()

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ContentViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.content_list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is ContentViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(contentList: List<Content>) {
        items = contentList
        notifyDataSetChanged()
    }

    class ContentViewHolder constructor(
        itemView: View
    ) : RecyclerView.ViewHolder(itemView) {

        private val card: MaterialCardView =
            itemView.findViewById(R.id.materialcardview_content_card)
        private val ivImage: ImageView = itemView.findViewById(R.id.imageview_content_image)
        private val tvName: TextView = itemView.findViewById(R.id.textview_content_title)
        private val tvReleaseDate: TextView = itemView.findViewById(R.id.textview_content_year)
        private val tvPrice: TextView = itemView.findViewById(R.id.textview_content_price)

        fun bind(content: Content) {
            displayImage(itemView.context, content.artworkUrl100, ivImage)

            card.setOnClickListener {
                UiUtil.navigateTo(
                    itemView,
                    R.id.action_homeFragment_to_detailFragment,
                    bundleOf(
                        "content" to content
                    )
                )
            }

            tvName.text = content.trackName
            content.releaseDate?.let { releasedDate ->
                if (content.releaseDate == null) {
                    tvReleaseDate.visibility = View.GONE
                } else {
                    val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    val cal = Calendar.getInstance()
                    cal.timeInMillis = simpleDateFormat.parse(releasedDate).time
                    tvReleaseDate.text = cal.get(Calendar.YEAR).toString()
                    tvReleaseDate.visibility = View.VISIBLE
                }
            }
            val currency = content.currency ?: "TRY"
            val price = content.trackPrice?.let { "%.2f".format(it) } ?: "0"
            tvPrice.text = "$currency $price"
        }

        private fun displayImage(context: Context, imgUrl: String?, imageView: ImageView) {
            val circularProgressDrawable = CircularProgressDrawable(context)
            circularProgressDrawable.setColorSchemeColors(
                ContextCompat.getColor(context, R.color.colorPrimary),
                ContextCompat.getColor(context, R.color.colorAccent),
                ContextCompat.getColor(context, R.color.colorPrimaryDark)
            )
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