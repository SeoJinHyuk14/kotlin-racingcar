package racing

fun main() {
    val carNames = inputCarName()
    val tryCount = inputTryCount()

    val cars = carNames.map { Car(it) }
    val carRacing = CarRacing(cars, tryCount)
    carRacing.execute()

    println("실행 결과")
    printResult(cars, tryCount)
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

    fun getWinner(): List<Car> {
        val maxPosition = cars.map { it.position }.max()
        return cars.filter { it.position == maxPosition }
    }
}
