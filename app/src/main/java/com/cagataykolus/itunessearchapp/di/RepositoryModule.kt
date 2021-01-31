package com.cagataykolus.itunessearchapp.di

import android.content.Context
import com.cagataykolus.itunessearchapp.data.mapper.ContentMapper
import com.cagataykolus.itunessearchapp.data.network.ApiServiceRetrofit
import com.cagataykolus.itunessearchapp.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

/**
 * Created by Çağatay Kölüş on 30.01.2020.
 * cagataykolus@gmail.com
 */
@InstallIn(ApplicationComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(
        @ApplicationContext appContext: Context,
        retrofit: ApiServiceRetrofit,
        contentEntityMapper: ContentMapper
    ): MainRepository {
        return MainRepository(
            appContext,
            retrofit,
            contentEntityMapper
        )
    }
}
