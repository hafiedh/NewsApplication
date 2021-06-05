package com.hafidh.newsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.hafidh.newsapp.adapter.PagerAdapter
import com.hafidh.newsapp.databinding.ActivityMainBinding
import com.hafidh.newsapp.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var sectionPagerAdapter: PagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val favoriteFragment = Class.forName("com.hafidh.favorite.FavoriteFragment")
        val fr = listOf(HomeFragment(), favoriteFragment.newInstance() as Fragment)
        val title = listOf(resources.getString(R.string.home),(resources.getString(R.string.favorite)))
        sectionPagerAdapter = PagerAdapter(fr,supportFragmentManager,lifecycle)
        binding.vp.adapter = sectionPagerAdapter
        TabLayoutMediator(binding.tab,binding.vp){tab,position->
            tab.text = title[position]
        }.attach()
    }
}