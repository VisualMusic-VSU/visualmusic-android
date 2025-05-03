package app.visualmusic.di.hilt

import app.visualmusic.core.repository.CoverRepository
import app.visualmusic.core.service.CoverService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreModule {
    @Provides
    @Singleton
    fun provideCoverService(
        coverRepository: CoverRepository
    ) : CoverService =
        CoverService(coverRepository)
}