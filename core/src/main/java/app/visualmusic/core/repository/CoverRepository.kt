package app.visualmusic.core.repository

import app.visualmusic.core.model.cover.CoverGroupDetailDto
import app.visualmusic.core.model.cover.CoverGroupItemDto
import app.visualmusic.core.utils.OperationResult

interface CoverRepository {
    suspend fun getAllCovers(): OperationResult<List<CoverGroupItemDto>>

    fun getAllGeneratedCoverGroups(): OperationResult<List<CoverGroupDetailDto>>

    fun getAllSavedCoverGroups(): OperationResult<List<CoverGroupDetailDto>>

    fun getCoverGroupById(id: Int): OperationResult<List<CoverGroupDetailDto>>
}