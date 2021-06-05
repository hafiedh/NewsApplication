package com.hafidh.newsapp.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class PagerAdapter(private val list: List<Fragment>, fm: FragmentManager, lifecycle: Lifecycle):
    FragmentStateAdapter(fm,lifecycle) {
    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment = list[position]
}