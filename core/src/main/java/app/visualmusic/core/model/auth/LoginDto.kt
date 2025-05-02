package app.visualmusic.core.model.auth

data class LoginDto(
    val username: String,
    val password: String,
    val deviceId: String
)
