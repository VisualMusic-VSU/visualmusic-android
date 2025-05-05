package app.visualmusic.core.repository

import app.visualmusic.core.model.cover.CoverGroupDetailDto
import app.visualmusic.core.model.cover.CoverGroupItemDto
import app.visualmusic.core.model.reference.Genre
import app.visualmusic.core.model.reference.Mood
import app.visualmusic.core.model.reference.Style
import app.visualmusic.core.utils.OperationResult

interface CoverRepository {
    suspend fun getAllCovers(): OperationResult<List<CoverGroupItemDto>>

    fun getAllGeneratedCoverGroups(): OperationResult<List<CoverGroupItemDto>>

    fun getAllSavedCoverGroups(): OperationResult<List<CoverGroupDetailDto>>

    fun getCoverGroupById(id: Int): OperationResult<List<CoverGroupDetailDto>>

    fun getAllGenres(): List<Genre>

    fun getAllMoods(): List<Mood>

    fun getAllStyles(): List<Style>
}