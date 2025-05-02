package app.visualmusic.core.repository

import app.visualmusic.core.model.cover.GenreDto
import app.visualmusic.core.model.cover.MoodDto
import app.visualmusic.core.model.cover.StyleDto
import app.visualmusic.core.model.gen.GenParamsDto
import app.visualmusic.core.model.gen.GenPromptDto
import app.visualmusic.core.utils.OperationResult

interface GenerationRepository {
    fun generateByParams(params: GenParamsDto): OperationResult<Nothing>

    fun generateByPrompt(prompt: GenPromptDto): OperationResult<Nothing>

    fun getAllGenres(): OperationResult<List<GenreDto>>

    fun getAllMoods(): OperationResult<List<MoodDto>>

    fun getAllStyles(): OperationResult<List<StyleDto>>
}