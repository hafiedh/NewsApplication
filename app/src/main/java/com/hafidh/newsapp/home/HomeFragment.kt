package com.hafidh.newsapp.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.hafidh.core.data.Resource
import com.hafidh.core.utis.Constans.EXTRA_DATA
import com.hafidh.newsapp.R
import com.hafidh.newsapp.adapter.ArticleAdapter
import com.hafidh.newsapp.databinding.FragmentHomeBinding
import com.hafidh.newsapp.detail.DetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var newsAdapter: ArticleAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            newsAdapter = ArticleAdapter()
            newsAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(EXTRA_DATA, selectedData)
                startActivity(intent)
            }
            getBreakingNews()
            with(binding.rvArticle) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                adapter = newsAdapter
            }

        }
    }

    override fun onResume() {
        super.onResume()
        getBreakingNews()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun getBreakingNews() {
        homeViewModel.breakingNews.observe(viewLifecycleOwner, { article ->
            if (article != null) {
                when (article) {
                    is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        newsAdapter.setData(article.data)
                    }
                    is Resource.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            context,
                            getString(R.string.something_wrong),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

}