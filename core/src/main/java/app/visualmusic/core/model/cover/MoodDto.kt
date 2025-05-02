package app.visualmusic.core.model.cover

data class MoodDto(
    override val id: Long,
    override val name: String
) : ReferenceItem()
