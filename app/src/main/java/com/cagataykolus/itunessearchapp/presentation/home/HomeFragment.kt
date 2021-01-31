package com.cagataykolus.itunessearchapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.cagataykolus.itunessearchapp.R
import com.cagataykolus.itunessearchapp.databinding.FragmentHomeBinding
import com.cagataykolus.itunessearchapp.domain.model.Media
import com.cagataykolus.itunessearchapp.domain.state.DataState
import com.cagataykolus.itunessearchapp.presentation.base.BaseViewBindingFragment
import com.cagataykolus.itunessearchapp.util.DebouncingQueryTextListener
import com.cagataykolus.itunessearchapp.util.KeyboardUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.appbar.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : BaseViewBindingFragment<FragmentHomeBinding>() {
    private val viewModel: HomeViewModel by viewModels()
    private lateinit var contentRecyclerAdapter: HomeAdapter
    private lateinit var searchView: SearchView

    override fun setBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding = FragmentHomeBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        displaySearchIllustration(true)
        initRecyclerView()
        initTopAppBar()
        initMenu()
        initMultiStateToggleButton()

        subscribeObservers()
    }

    private fun initMultiStateToggleButton() {
        binding.multistatetogglebuttonHomeSelection.setOnValueChangedListener { position ->
            if (searchView.query.toString().isNotEmpty()) {
                viewModel.setStateEvent(
                    MainIntent.GetContentsIntent(
                        searchView.query.toString(),
                        Media.fromString(
                            binding.multistatetogglebuttonHomeSelection.texts[binding.multistatetogglebuttonHomeSelection.value].toString()
                                .toLowerCase()
                        )
                    )
                )
            }
        }
        binding.multistatetogglebuttonHomeSelection.value = 0
    }

    private fun initRecyclerView() {
        binding.recyclerviewHomeList.apply {
            layoutManager = GridLayoutManager(this.context, 2)

            contentRecyclerAdapter = HomeAdapter()
            adapter = contentRecyclerAdapter
        }
    }

    private fun initTopAppBar() {
        topAppBar.inflateMenu(R.menu.menu)
        topAppBar.navigationIcon = null
        topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_search -> {
                    true
                }
                else -> false
            }
        }
    }

    private fun initMenu() {
        val searchItem = topAppBar.menu.findItem(R.id.action_search)
        if (searchItem != null) {
            searchView = searchItem.actionView as SearchView
        }

        // Avoid search spam
        searchView.setOnQueryTextListener(
            DebouncingQueryTextListener(
                this.lifecycle
            ) { newText ->
                newText?.let { txt ->
                    if (txt.isNotEmpty()) {
                        viewModel.setStateEvent(
                            MainIntent.GetContentsIntent(
                                txt,
                                Media.fromString(
                                    binding.multistatetogglebuttonHomeSelection.texts[binding.multistatetogglebuttonHomeSelection.value].toString()
                                        .toLowerCase()
                                )
                            )
                        )
                    }
                }
            }
        )
    }

    private fun displayProgressBar(isDisplayed: Boolean) {
        binding.appbar.progressbar.visibility = when (isDisplayed) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun displaySearchIllustration(isDisplayed: Boolean) {
        binding.imageviewHomeSearchIllustration.visibility = when (isDisplayed) {
            true -> View.VISIBLE
            else -> View.GONE
        }
    }

    private fun displayToast(message: String) {
        Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            KeyboardUtil.hideKeyboard(requireActivity())
            when (dataState) {

                is DataState.SUCCESS<MainDataState> -> {
                    displayProgressBar(false)
                    dataState.data?.let { it ->
                        when {
                            it.contents.isNotEmpty() -> {
                                contentRecyclerAdapter.submitList(it.contents)
                                displaySearchIllustration(false)
                            }
                            it.contents.isEmpty() -> {
                                val toastMsg = getString(R.string.empty_results)
                                displayToast(toastMsg)
                            }
                        }
                    }
                }

                is DataState.LOADING -> {
                    displayProgressBar(dataState.loading)
                }

                is DataState.ERROR -> {
                    displayProgressBar(dataState.loading)

                    dataState.stateMessage?.message?.let {
                        displayToast(it)
                    }
                }
            }
        })
    }
}