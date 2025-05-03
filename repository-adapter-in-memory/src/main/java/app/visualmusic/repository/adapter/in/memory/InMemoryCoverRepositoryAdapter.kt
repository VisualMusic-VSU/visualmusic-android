package app.visualmusic.repository.adapter.`in`.memory

import app.visualmusic.core.model.cover.CoverGroupDetailDto
import app.visualmusic.core.model.cover.CoverGroupItemDto
import app.visualmusic.core.repository.CoverRepository
import app.visualmusic.core.utils.OperationResult

class InMemoryCoverRepositoryAdapter : CoverRepository {
    override suspend fun getAllCovers(): OperationResult<List<CoverGroupItemDto>> {
        return OperationResult.Success(
            listOf(
                CoverGroupItemDto(
                    id = 1L,
                    ownerUsername = "KraboID",
                    title = "Натуральные вайбы",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_1.toString(),
                    )
                ),
                CoverGroupItemDto(
                    id = 2L,
                    ownerUsername = "bob_marley",
                    title = "Неон",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_2_1.toString(),
                        R.drawable.mock_cover_image_2_2.toString(),
                        R.drawable.mock_cover_image_2_3.toString(),
                        R.drawable.mock_cover_image_2_4.toString(),
                    )
                ),
                CoverGroupItemDto(
                    id = 3L,
                    ownerUsername = "omega",
                    title = "Пианино в лесу. Светло. Никого нет",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_3_1.toString(),
                        R.drawable.mock_cover_image_3_2.toString(),
                        R.drawable.mock_cover_image_3_3.toString(),
                    )
                ),
                CoverGroupItemDto(
                    id = 4L,
                    ownerUsername = "diana",
                    title = "Абстрактные формы, аля нейросеть переехал грузовик с розовой косметикой",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_4.toString(),
                    )
                ),
                CoverGroupItemDto(
                    id = 5L,
                    ownerUsername = "palchIkoviyMANIAC",
                    title = "ПАЛЬЧИКИ",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_5_1.toString(),
                        R.drawable.mock_cover_image_5_2.toString(),
                        R.drawable.mock_cover_image_5_3.toString(),
                        )
                ),
                CoverGroupItemDto(
                    id = 6L,
                    ownerUsername = "sadyyyyyy",
                    title = "Осколочки туда-сюда",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_6.toString(),
                    )
                ),
                CoverGroupItemDto(
                    id = 7L,
                    ownerUsername = "georgina",
                    title = "Красивая аниме девочка",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_7_1.toString(),
                        R.drawable.mock_cover_image_7_2.toString(),
                    )
                ),
                CoverGroupItemDto(
                    id = 8L,
                    ownerUsername = "hahahaahahahaah",
                    title = "Пустыня с мерцающим миражом",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_8_1.toString(),
                        R.drawable.mock_cover_image_8_2.toString(),
                    )
                ),
                CoverGroupItemDto(
                    id = 8L,
                    ownerUsername = "qwerty",
                    title = "Retro",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_9_1.toString(),
                        R.drawable.mock_cover_image_9_2.toString(),
                    )
                ),
                CoverGroupItemDto(
                    id = 8L,
                    ownerUsername = "gonefludd",
                    title = "Обложку к рекламе магазина кроссовок",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_10.toString()
                    )
                ),
                CoverGroupItemDto(
                    id = 8L,
                    ownerUsername = "sqwozbab",
                    title = "natural",
                    imageUrls = listOf(
                        R.drawable.mock_cover_image_11_1.toString(),
                        R.drawable.mock_cover_image_11_2.toString(),
                    )
                )
            )
        )
    }

    override fun getAllGeneratedCoverGroups(): OperationResult<List<CoverGroupDetailDto>> {
        return OperationResult.Loading
    }

    override fun getAllSavedCoverGroups(): OperationResult<List<CoverGroupDetailDto>> {
        return OperationResult.Loading
    }

    override fun getCoverGroupById(id: Int): OperationResult<List<CoverGroupDetailDto>> {
        return OperationResult.Loading
    }
}