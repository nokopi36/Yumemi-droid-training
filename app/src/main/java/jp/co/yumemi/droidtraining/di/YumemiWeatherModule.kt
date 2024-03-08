package jp.co.yumemi.droidtraining.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import jp.co.yumemi.api.YumemiWeather
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object YumemiWeatherModule {
    @Provides
    @Singleton
    fun provideYumemiWeather(@ApplicationContext context: Context): YumemiWeather {
        return YumemiWeather(context = context)
    }
}
