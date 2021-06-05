package com.hafidh.favorite.di

import android.content.Context
import com.hafidh.favorite.FavoriteFragment
import com.hafidh.newsapp.di.AppDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [AppDependencies::class])
interface AppComponent {
    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(appDependencies: AppDependencies): Builder
        fun build(): AppComponent
    }
}