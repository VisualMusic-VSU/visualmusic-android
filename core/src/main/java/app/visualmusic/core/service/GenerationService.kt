package app.visualmusic.core.service

import app.visualmusic.core.model.gen.GenParamsDto
import app.visualmusic.core.model.gen.GenPromptDto
import app.visualmusic.core.repository.GenerationRepository
import app.visualmusic.core.utils.OperationResult

class GenerationService(
    private val generationRepository: GenerationRepository
) {
    fun generateByParams(params: GenParamsDto): OperationResult<Nothing> {
        return generationRepository.generateByParams(params)
    }

    fun generateByPrompt(prompt: GenPromptDto): OperationResult<Nothing> {
        return generationRepository.generateByPrompt(prompt)
    }
}