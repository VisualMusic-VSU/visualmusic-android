package app.visualmusic.core.model.cover

data class CoverGroupItemDto(
    val id: Long,
    val ownerUsername: String?,
    val title: String,
    val imageUrls: List<String>
)