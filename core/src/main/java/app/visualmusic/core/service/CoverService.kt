package app.visualmusic.core.service

import app.visualmusic.core.model.cover.CoverGroupDetailDto
import app.visualmusic.core.model.cover.CoverItemDto
import app.visualmusic.core.repository.CoverRepository
import app.visualmusic.core.utils.OperationResult

class CoverService(
    private val coverRepository: CoverRepository
) {
    fun getAllCovers(): OperationResult<List<CoverItemDto>> {
        return coverRepository.getAllCovers()
    }

    fun getAllGeneratedCoverGroups(): OperationResult<List<CoverGroupDetailDto>> {
        return coverRepository.getAllGeneratedCoverGroups()
    }

    fun getAllSavedCoverGroups(): OperationResult<List<CoverGroupDetailDto>> {
        return coverRepository.getAllSavedCoverGroups()
    }

    fun getCoverGroupById(id: Int): OperationResult<List<CoverGroupDetailDto>> {
        return coverRepository.getCoverGroupById(id)
    }
}