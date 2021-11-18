package com.checkout51.challenge.feature.offers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.checkout51.challenge.core.GridSpacingItemDecoration
import com.checkout51.challenge.core.dp
import com.checkout51.challenge.core.px
import com.checkout51.challenge.databinding.ActivityOffersBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class OffersActivity : AppCompatActivity() {

    private val viewModel by viewModel<OffersViewModel>()
    private lateinit var binding: ActivityOffersBinding
    private lateinit var offersAdapter: OffersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOffersBinding.inflate(layoutInflater).apply {
            setContentView(this.root)
        }

        offersAdapter = OffersAdapter()
        binding.rvOffers.layoutManager = GridLayoutManager(this, 2)
        binding.rvOffers.addItemDecoration(GridSpacingItemDecoration(2, 10.dp, false))
        binding.rvOffers.adapter = offersAdapter
        binding.sortView.onSortTypeChanged {
            viewModel.sortOffersBy(it)
        }

        if (savedInstanceState == null) {
            viewModel.fetchOffers()
        }

        viewModel.viewState.observe(this, ::handleViewState)
    }

    private fun handleViewState(viewState: OffersViewState) {
        when (viewState) {
            is OffersViewState.ShowOffers -> {
                offersAdapter.submitList(viewState.offers) {
                    binding.rvOffers.scrollToPosition(0)
                }
            }
            is OffersViewState.ShowMessage -> {
                binding.tvMessage.visibility = View.VISIBLE
                binding.tvMessage.text = viewState.message
            }
            is OffersViewState.ShowLoading -> {
                binding.progressBar.visibility = View.VISIBLE
            }
            is OffersViewState.HideLoading -> {
                binding.progressBar.visibility = View.GONE
            }
        }
    }
}