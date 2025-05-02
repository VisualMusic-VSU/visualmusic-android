package app.visualmusic.core.model.cover

import java.time.Instant

data class CoverGroupDetailDto(
    val id: Long,
    val ownerUsername: String?,
    val title: String,
    val isPrivate: Boolean,
    val createdAt: Instant,

    val imageUrls: List<Int>,

    val genres: List<String>?,
    val mood: String?,
    val style: String?,
    val text: String?,
    val prompt: String?
)
