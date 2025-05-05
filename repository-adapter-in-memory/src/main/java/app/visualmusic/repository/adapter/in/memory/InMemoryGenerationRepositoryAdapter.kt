package app.visualmusic.repository.adapter.`in`.memory

import app.visualmusic.core.model.gen.GenParamsDto
import app.visualmusic.core.model.gen.GenPromptDto
import app.visualmusic.core.repository.GenerationRepository
import app.visualmusic.core.utils.OperationResult

class InMemoryGenerationRepositoryAdapter : GenerationRepository {
    override fun generateByParams(params: GenParamsDto): OperationResult<Nothing> {
        return OperationResult.Loading
    }

    override fun generateByPrompt(prompt: GenPromptDto): OperationResult<Nothing> {
        return OperationResult.Loading
    }
}