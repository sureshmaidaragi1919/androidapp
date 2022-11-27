package com.example.androidcleanarchapp.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.androidcleanarchapp.data.EntryModel
import com.example.androidcleanarchapp.databinding.FragmentMainBinding
import com.example.androidcleanarchapp.presentation.itemsview.ItemsAdapter
import com.example.androidcleanarchapp.util.makeGone
import com.example.androidcleanarchapp.util.makeVisible
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private var itemsAdapter: ItemsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchEntry()
        observeLiveData()
        initSwipeToRefresh()

    }

    private fun initSwipeToRefresh() {
        binding.swipeToRefresh.setOnRefreshListener {
            binding.itemRc.makeGone()
            viewModel.fetchEntry()
        }
    }

    private fun observeLiveData() {
        viewModel.data.observeForever {
            when (it) {
                MainViewModel.State.Loading -> {
                    binding.swipeToRefresh.isRefreshing = true
                }
                is MainViewModel.State.Success -> {
                    binding.swipeToRefresh.isRefreshing = false
                    binding.errorMessage.makeGone()
                    setUpAdapter(it.model)
                }
                is MainViewModel.State.Failed -> {
                    binding.swipeToRefresh.isRefreshing = true

                    binding.errorMessage.apply {
                        makeVisible()
                        text = it.throwable.message
                    }
                }
            }
        }
    }

    private fun setUpAdapter(model: EntryModel) {

        itemsAdapter = ItemsAdapter()
        binding.itemRc.apply {
            makeVisible()
            adapter = itemsAdapter
        }
        itemsAdapter?.itemsList = model.entriesList
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}