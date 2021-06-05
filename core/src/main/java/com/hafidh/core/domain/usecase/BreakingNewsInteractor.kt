package com.hafidh.core.domain.usecase

import com.hafidh.core.domain.model.Article
import com.hafidh.core.domain.repository.INewsRepository
import javax.inject.Inject

class BreakingNewsInteractor @Inject constructor(private val breakingINewsRepository: INewsRepository) :
    BreakingNewsUseCase {

    override fun getAllBreakingNews() = breakingINewsRepository.getAllBreakingNews()

    override fun getFavoriteNews() = breakingINewsRepository.getFavoriteNews()

    override fun setFavoriteNews(article: Article, state: Boolean) =
        breakingINewsRepository.setFavoriteNews(article, state)

}