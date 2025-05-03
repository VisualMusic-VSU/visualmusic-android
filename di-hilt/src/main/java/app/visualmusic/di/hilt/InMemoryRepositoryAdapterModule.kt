package app.visualmusic.di.hilt

import app.visualmusic.core.repository.CoverRepository
import app.visualmusic.core.repository.GenerationRepository
import app.visualmusic.repository.adapter.`in`.memory.InMemoryCoverRepositoryAdapter
import app.visualmusic.repository.adapter.`in`.memory.InMemoryGenerationRepositoryAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class InMemoryRepositoryAdapterModule {
    @Provides
    @Singleton
    fun provideCoverRepository(): CoverRepository =
        InMemoryCoverRepositoryAdapter()

    @Provides
    @Singleton
    fun provideGenerationRepository(): GenerationRepository =
        InMemoryGenerationRepositoryAdapter()
}