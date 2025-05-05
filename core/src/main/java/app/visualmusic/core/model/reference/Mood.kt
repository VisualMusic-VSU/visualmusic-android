package app.visualmusic.core.model.reference

data class Mood(
    override val id: Long,
    override val name: String
) : ReferenceItem()
