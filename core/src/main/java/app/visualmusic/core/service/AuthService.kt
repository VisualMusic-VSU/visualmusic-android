package app.visualmusic.core.service

import app.visualmusic.core.model.auth.LoginDto
import app.visualmusic.core.utils.OperationResult

class AuthService {
    fun login(data: LoginDto): OperationResult<Nothing> {
        return OperationResult.Loading
    }

    fun register(data: LoginDto): OperationResult<Nothing> {
        return OperationResult.Loading
    }
}