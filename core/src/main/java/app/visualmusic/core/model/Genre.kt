package app.visualmusic.core.model

data class Genre(
    override val id: Long,
    override val name: String
) : ReferenceItem()
