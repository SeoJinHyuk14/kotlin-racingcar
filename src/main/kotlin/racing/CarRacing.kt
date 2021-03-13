package racing

import racing.domain.Car
import racing.domain.CarNameParser
import racing.view.inputCarName
import racing.view.inputTryCount
import racing.view.printResult
import racing.view.printWinner

fun main() {
    val carNameLine = inputCarName()
    val tryCount = inputTryCount()
    val carNames = CarNameParser.parse(carNameLine)

    val cars = carNames.map { Car(it) }
    val carRacing = CarRacing(cars, tryCount)
    carRacing.execute()

    println("실행 결과")
    val recordsPerCarName = cars.associateBy({ it.name }, { it.getRecords() })
    printResult(recordsPerCarName, tryCount)
    printWinner(carRacing.getWinners().joinToString(", ") { it.name })
}

class CarRacing(private val cars: List<Car>, private val tryCount: Int) {

    fun execute() {
        repeat(tryCount) {
            executeCarGo()
        }
    }

    private fun executeCarGo() {
        cars.forEach {
            it.tryGo()
        }
    }

    fun getWinners(): List<Car> {
        val maxPosition = cars.map { it.position }.max()
        require(maxPosition != null) { "car name이 입력되었다면, maxPosition은 null이 될수 없습니다." }

        return cars.filter { it.position == maxPosition }
    }
}
