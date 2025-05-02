package app.visualmusic.core.model.auth

data class RegisterDto(
    val username: String,
    val email: String,
    val password: String
)
