package app.visualmusic.presentation.generation.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import app.visualmusic.common.bottomsheet.MultipleChoiceListBottomSheet
import app.visualmusic.common.bottomsheet.SingleChoiceListBottomSheet
import app.visualmusic.core.model.cover.GenreDto
import app.visualmusic.core.model.cover.MoodDto
import app.visualmusic.core.model.cover.ReferenceItem
import app.visualmusic.core.model.cover.StyleDto
import app.visualmusic.databinding.FragmentDetailedGenerationBinding

class DetailedGenerationFragment : Fragment() {
    private lateinit var binding: FragmentDetailedGenerationBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailedGenerationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSettingsBtnListeners()
    }

    private fun setupSettingsBtnListeners() {
        binding.apply {
            genresSetting.onSettingBtnClick {
                showMultipleBottomSheet(
                    "Жанры", listOf<ReferenceItem>(
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
            moodSetting.onSettingBtnClick {
                showSingleBottomSheet(
                    "Настроение", listOf<ReferenceItem>(
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
            styleSetting.onSettingBtnClick {
                showSingleBottomSheet(
                    "Стиль", listOf(
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
            textSetting.onSettingBtnClick {
                showTextEditorBottomSheet()
            }
        }
    }

    private fun showMultipleBottomSheet(title: String, items: List<ReferenceItem>) {
        MultipleChoiceListBottomSheet(title).apply {
            mockItems(items)
        }.show(childFragmentManager, MultipleChoiceListBottomSheet.TAG)
    }

    private fun showSingleBottomSheet(title: String, items: List<ReferenceItem>) {
        SingleChoiceListBottomSheet(title).apply {
            mockItems(items)
        }.show(childFragmentManager, MultipleChoiceListBottomSheet.TAG)
    }

    private fun showTextEditorBottomSheet() {
        TextEditorBottomSheet().show(childFragmentManager, TextEditorBottomSheet.TAG)
    }
}