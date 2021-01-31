package com.cagataykolus.itunessearchapp.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.cagataykolus.itunessearchapp.R
import com.cagataykolus.itunessearchapp.databinding.FragmentDetailBinding
import com.cagataykolus.itunessearchapp.domain.model.Content
import com.cagataykolus.itunessearchapp.presentation.base.BaseViewBindingFragment
import com.cagataykolus.itunessearchapp.util.UiUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class DetailFragment : BaseViewBindingFragment<FragmentDetailBinding>() {

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater)

    companion object {
        val ARGS_CONTENT = "content"
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        val content = arguments?.getParcelable<Content>(ARGS_CONTENT)
        content?.let { displayContentDetails(it) }
    }

    private fun displayContentDetails(content: Content) {
        binding.textviewDetailTitle.text = content.trackName
        binding.textviewDetailArtist.text = content.artistName

        content.releaseDate?.let { releasedDate ->
            if (content.releaseDate == null) {
                binding.textviewDetailYear.visibility = View.GONE
            } else {
                val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                val cal = Calendar.getInstance()
                cal.timeInMillis = simpleDateFormat.parse(releasedDate).time
                binding.textviewDetailYear.text = cal.get(Calendar.YEAR).toString()
                binding.textviewDetailYear.visibility = View.VISIBLE
            }
        }

        val currency = content.currency ?: "TRY"
        val price = content.trackPrice?.let { "%.2f".format(it) } ?: "0"
        binding.textviewDetailPrice.text = "$currency $price"

        binding.textviewDetailDescription.let {
            var description = content.longDescription ?: ""
            if (description.isEmpty()) {
                description = content.shortDescription ?: "..."
            }
            it.text = description
        }

        UiUtil.displayImage(requireContext(), content.artworkUrl100, binding.imageviewDetailImage)
    }

    private fun initViews() {
        binding.appbar.topAppBar.navigationIcon =
            ContextCompat.getDrawable(this.requireContext(), R.drawable.ic_arrow_back)
        binding.appbar.topAppBar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }
}