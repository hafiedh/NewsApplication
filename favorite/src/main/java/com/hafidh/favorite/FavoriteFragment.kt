package com.hafidh.favorite

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafidh.core.utis.Constans
import com.hafidh.favorite.di.DaggerAppComponent
import com.hafidh.newsapp.adapter.ArticleAdapter
import com.hafidh.newsapp.databinding.FragmentFavoriteBinding
import com.hafidh.newsapp.detail.DetailActivity
import com.hafidh.newsapp.di.AppDependencies
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val favViewModel: FavoriteViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val breakingNewsAdapter = ArticleAdapter()
            binding.rvFavorite.layoutManager = LinearLayoutManager(context)
            binding.rvFavorite.adapter = breakingNewsAdapter
            breakingNewsAdapter.onItemClick = { selectedArticle ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(Constans.EXTRA_DATA, selectedArticle)
                startActivity(intent)
            }
            favViewModel.favoriteBreakingNews.observe(viewLifecycleOwner, { listArticle ->
                if (listArticle.isEmpty()){
                    binding.tvEmpty.isVisible = true
                    breakingNewsAdapter.setData(listArticle)
                }else{
                    binding.tvEmpty.isVisible = false
                    breakingNewsAdapter.setData(listArticle)
                }
            })
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        DaggerAppComponent.builder().context(
            context
        ).appDependencies(
            EntryPointAccessors.fromApplication(
                context,
                AppDependencies::class.java
            )
        ).build().inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}