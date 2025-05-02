package app.visualmusic.core.model

data class Mood(
    override val id: Long,
    override val name: String
) : ReferenceItem()
