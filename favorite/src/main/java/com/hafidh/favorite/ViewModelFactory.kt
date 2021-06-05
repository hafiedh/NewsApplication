package com.hafidh.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hafidh.core.domain.usecase.BreakingNewsUseCase
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val breakingNewsUseCase: BreakingNewsUseCase): ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(breakingNewsUseCase) as T
            }
            else -> throw Throwable("Unkwnown Viewmodel class: " + modelClass.name)
        }

}