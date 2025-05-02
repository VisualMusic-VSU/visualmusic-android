package app.visualmusic.core.model.gen

data class GenParamsDto(
    val audio: ByteArray?,
    val genreIds: List<Int>?,
    val moodId: Int?,
    val styleId: Int?,
    val text: String?
)