package app.visualmusic.core.repository

import app.visualmusic.core.model.auth.JwtTokensDto
import app.visualmusic.core.utils.OperationResult

interface AuthRepository {
    fun login() : OperationResult<JwtTokensDto>

    fun register() : OperationResult<Nothing>
}