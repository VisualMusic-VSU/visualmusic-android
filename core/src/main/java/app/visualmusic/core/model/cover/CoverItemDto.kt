package app.visualmusic.core.model.cover

data class CoverItemDto (
    val id: Long,
    val imageUrl: String,
    val ownerUsername: String?,
    val title: String
)