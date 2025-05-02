package app.visualmusic.core.repository

import app.visualmusic.core.model.cover.CoverGroupDetailDto
import app.visualmusic.core.model.cover.CoverItemDto
import app.visualmusic.core.utils.OperationResult

interface CoverRepository {
    fun getAllCovers(): OperationResult<List<CoverItemDto>>

    fun getAllGeneratedCoverGroups(): OperationResult<List<CoverGroupDetailDto>>

    fun getAllSavedCoverGroups(): OperationResult<List<CoverGroupDetailDto>>

    fun getCoverGroupById(id: Int): OperationResult<List<CoverGroupDetailDto>>
}