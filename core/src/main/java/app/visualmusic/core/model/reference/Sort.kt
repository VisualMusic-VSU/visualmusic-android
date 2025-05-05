package app.visualmusic.core.model.reference

data class Sort(
    override val id: Long,
    override val name: String
) : ReferenceItem()