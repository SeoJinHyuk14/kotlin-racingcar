package racing.domain

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CarNameParserTest {

    @Test
    fun `입력받은 이름이 ,기준으로 잘 스플릿된다`() {
        val carNames = CarNameParser.parse("서진혁,카샤,next,step")

        assertEquals(listOf("서진혁", "카샤", "next", "step"), carNames)
    }

    @Test
    fun `이름이 5글자가 넘으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            CarNameParser.parse("서진혁,카샤,nextstep")
        }
    }
}
