package app.visualmusic.core.model.reference

data class Genre(
    override val id: Long,
    override val name: String
) : ReferenceItem()
