package app.visualmusic.core.model.reference

data class Style(
    override val id: Long,
    override val name: String
) : ReferenceItem()
