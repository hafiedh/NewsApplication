package com.hafidh.newsapp.detail

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.hafidh.newsapp.R
import com.hafidh.core.domain.model.Article
import com.hafidh.core.utis.Constans.EXTRA_DATA
import com.hafidh.newsapp.databinding.ActivityDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val detailViewModel: DetailViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val detailArticle = intent.getParcelableExtra<Article>(EXTRA_DATA)

        binding.webView.apply {
            webViewClient = WebViewClient()
            detailArticle?.url.let {
                if (it != null) {
                    loadUrl(it)
                }
            }
        }
        var isFavorited = detailArticle?.isFavorite
        if (isFavorited != null) {
            setStatusFavorite(isFavorited)
        }
        binding.fab.setOnClickListener {
            isFavorited = when (isFavorited) {
                true -> false
                else -> true
            }
            if (detailArticle != null) {
                detailViewModel.setFavoriteBreakingNews(detailArticle, isFavorited!!)
                setStatusFavorite(isFavorited!!)
            }
        }

    }

    private fun setStatusFavorite(state: Boolean) {
        if (state) {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_red
                )
            )
        } else {
            binding.fab.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_black
                )
            )
        }
    }
}