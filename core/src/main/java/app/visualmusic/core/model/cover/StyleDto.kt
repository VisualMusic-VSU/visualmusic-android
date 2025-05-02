package app.visualmusic.core.model.cover

data class StyleDto(
    override val id: Long,
    override val name: String
) : ReferenceItem()
