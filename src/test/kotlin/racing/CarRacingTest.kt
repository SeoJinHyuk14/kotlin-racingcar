package racing

import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.greaterThan
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import racing.domain.Car
import racing.domain.CarCollection

class CarRacingTest {

    @Test
    fun `차량 댓수와 repeat 횟수가 동일한지 확인한다`() {
        assertThat(cars.size, `is`(CAR_COUNT))
        cars.forEach {
            assertThat(it.getRecords().size, `is`(TRY_COUNT))
        }
    }

    @Test
    fun `승자끼리는 position이 항상 같아야한다`() {
        val winners = carRacing.getWinners()
        val positions = cars.filter { winners.contains(it) }.map { it.position }

        assertThat(positions.min(), `is`(positions.max()))
    }

    @Test
    fun `승자는 다른 사람보다 position 값이 커야한다`() {
        val winners = carRacing.getWinners()
        val losers = cars.filter { !winners.contains(it) }

        val winnerPosition = winners.map { it.position }.first()
        val losersMaxPosition = losers.map { it.position }.max() ?: 0

        assertThat(winnerPosition, greaterThan(losersMaxPosition))
    }

    companion object {
        private const val CAR_COUNT = 3
        private const val TRY_COUNT = 7
        private val cars = listOf(Car("서진혁"), Car("카샤"), Car("nextstep"))
        private val carCollection = CarCollection(cars)
        private val carRacing = CarRacing(carCollection, TRY_COUNT)

        @BeforeAll
        @JvmStatic
        fun setUp() {
            carRacing.execute()
        }
    }
}
