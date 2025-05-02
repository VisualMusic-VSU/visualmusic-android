package app.visualmusic.core.model.cover

data class GenreDto(
    override val id: Long,
    override val name: String
) : ReferenceItem()
