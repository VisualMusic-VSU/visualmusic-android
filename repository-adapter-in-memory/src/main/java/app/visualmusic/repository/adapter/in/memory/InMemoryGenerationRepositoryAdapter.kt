package app.visualmusic.repository.adapter.`in`.memory

import app.visualmusic.core.model.cover.GenreDto
import app.visualmusic.core.model.cover.MoodDto
import app.visualmusic.core.model.cover.StyleDto
import app.visualmusic.core.model.gen.GenParamsDto
import app.visualmusic.core.model.gen.GenPromptDto
import app.visualmusic.core.repository.GenerationRepository
import app.visualmusic.core.utils.OperationResult

class InMemoryGenerationRepositoryAdapter : GenerationRepository {
    override fun generateByParams(params: GenParamsDto): OperationResult<Nothing> {
        return OperationResult.Loading
    }

    override fun generateByPrompt(prompt: GenPromptDto): OperationResult<Nothing> {
        return OperationResult.Loading
    }

    override fun getAllGenres(): OperationResult<List<GenreDto>> {
        return OperationResult.Success<List<GenreDto>>(
            listOf(
                GenreDto(1, "Рэп"),
                GenreDto(2, "Трэп"),
                GenreDto(3, "Бум-бэп"),
                GenreDto(4, "Дрилл"),
                GenreDto(5, "Хард-рок"),
                GenreDto(6, "Альтернативный рок"),
                GenreDto(7, "Метал"),
                GenreDto(8, "Хаус"),
                GenreDto(9, "Техно"),
                GenreDto(10, "Драм-н-бейс"),
                GenreDto(11, "Смуз-джаз"),
                GenreDto(12, "Бибоп"),
                GenreDto(13, "Фьюжн"),
                GenreDto(14, "Симфония"),
                GenreDto(15, "Оркестровая музыка"),
                GenreDto(16, "Камерная музыка")
            )
        )
    }

    override fun getAllMoods(): OperationResult<List<MoodDto>> {
        return OperationResult.Success<List<MoodDto>>(
            listOf(
                MoodDto(1, "Весёлое"),
                MoodDto(2, "Нейтральное"),
                MoodDto(3, "Грустное"),
                MoodDto(4, "Яростное"),
                MoodDto(5, "Интроспективное"),
                MoodDto(6, "Напористое"),
                MoodDto(7, "Брутальное"),
                MoodDto(8, "Энергичное"),
                MoodDto(9, "Успокаивающее"),
                MoodDto(10, "Беззаботное"),
                MoodDto(11, "Уверенное"),
                MoodDto(12, "Танцевальное"),
                MoodDto(13, "Замысловатое"),
                MoodDto(14, "Величественное")
            )
        )
    }

    override fun getAllStyles(): OperationResult<List<StyleDto>> {
        return OperationResult.Success<List<StyleDto>>(
            listOf(
                StyleDto(1, "Детальное фото"),
                StyleDto(2, "Малевич"),
                StyleDto(3, "Студийное фото"),
                StyleDto(4, "Киберпанк"),
                StyleDto(5, "Айвазовский"),
                StyleDto(6, "Картина маслом"),
                StyleDto(7, "3D рендер"),
                StyleDto(8, "Портретное фото"),
                StyleDto(9, "Цифровая живопись"),
                StyleDto(10, "Мультфильм"),
                StyleDto(11, "Рисунок карандашом"),
                StyleDto(12, "Классицизм"),
                StyleDto(13, "Хохлома"),
                StyleDto(14, "Пикассо"),
                StyleDto(15, "Пиксель арт"),
                StyleDto(16, "Кандинский"),
                StyleDto(17, "Аниме")
            )
        )
    }
}