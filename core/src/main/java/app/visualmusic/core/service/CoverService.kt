package app.visualmusic.core.service

import app.visualmusic.core.model.cover.CoverGroupDetailDto
import app.visualmusic.core.model.cover.CoverGroupItemDto
import app.visualmusic.core.model.reference.Genre
import app.visualmusic.core.model.reference.Mood
import app.visualmusic.core.model.reference.Sort
import app.visualmusic.core.model.reference.Style
import app.visualmusic.core.repository.CoverRepository
import app.visualmusic.core.utils.OperationResult

class CoverService(
    private val coverRepository: CoverRepository
) {
    suspend fun getAllCovers(): OperationResult<List<CoverGroupItemDto>> {
        return coverRepository.getAllCovers()
    }

    fun getAllGeneratedCoverGroups(): OperationResult<List<CoverGroupItemDto>> {
        return coverRepository.getAllGeneratedCoverGroups()
    }

    fun getAllSavedCoverGroups(): OperationResult<List<CoverGroupDetailDto>> {
        return coverRepository.getAllSavedCoverGroups()
    }

    fun getCoverGroupById(id: Int): OperationResult<List<CoverGroupDetailDto>> {
        return coverRepository.getCoverGroupById(id)
    }

    fun getAllSorts(): List<Sort> = listOf(
        Sort(1, "По популярности"),
        Sort(2, "По дате загрузки")
    )

    fun getAllGenres(): List<Genre> {
        return coverRepository.getAllGenres()
    }

    fun getAllMoods(): List<Mood> {
        return coverRepository.getAllMoods()
    }

    fun getAllStyles(): List<Style> {
        return coverRepository.getAllStyles()
    }
}