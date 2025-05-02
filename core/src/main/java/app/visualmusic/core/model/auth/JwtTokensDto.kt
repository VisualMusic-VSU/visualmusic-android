package app.visualmusic.core.model.auth

data class JwtTokensDto(
    val accessToken: String,
    val refreshToken: String
)