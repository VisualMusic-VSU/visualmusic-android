package app.visualmusic.core.model

data class Style(
    override val id: Long,
    override val name: String
) : ReferenceItem()
