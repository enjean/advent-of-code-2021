package day2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class SubLocationWithAimTest {
    @Nested
    inner class ApplyCommand {
        @Test
        fun `forward 5 adds 5 to your horizontal position - Because your aim is 0, your depth does not change`() {
            val startingPosition = SubLocationWithAim(horizontalPosition = 0, depth = 0, aim = 0)
            val result = startingPosition.applyCommand(Command(direction = Direction.FORWARD, units = 5))
            assertEquals(SubLocationWithAim(horizontalPosition = 5, depth = 0, aim = 0), result)
        }

        @Test
        fun `down 5 adds 5 to your aim`() {
            val startingPosition = SubLocationWithAim(horizontalPosition = 5, depth = 0, aim = 0)
            val result = startingPosition.applyCommand(Command(direction = Direction.DOWN, units = 5))
            assertEquals(SubLocationWithAim(horizontalPosition = 5, depth = 0, aim = 5), result)
        }

        @Test
        fun `forward 8 adds 8 to your horizontal position - Because your aim is 5, your depth increases by 8X5=40`() {
            val startingPosition = SubLocationWithAim(horizontalPosition = 5, depth = 0, aim = 5)
            val result = startingPosition.applyCommand(Command(direction = Direction.FORWARD, units = 8))
            assertEquals(SubLocationWithAim(horizontalPosition = 13, depth = 40, aim = 5), result)
        }

        @Test
        fun `up 3 decreases your aim by 3`() {
            val startingPosition = SubLocationWithAim(horizontalPosition = 13, depth = 40, aim = 5)
            val result = startingPosition.applyCommand(Command(direction = Direction.UP, units = 3))
            assertEquals(SubLocationWithAim(horizontalPosition = 13, depth = 40, aim = 2), result)
        }

        @Test
        fun `down 8 adds 8 to your aim`() {
            val startingPosition = SubLocationWithAim(horizontalPosition = 13, depth = 40, aim = 2)
            val result = startingPosition.applyCommand(Command(direction = Direction.DOWN, units = 8))
            assertEquals(SubLocationWithAim(horizontalPosition = 13, depth = 40, aim = 10), result)
        }

        @Test
        fun `forward 2 adds 2 to your horizontal position - Because your aim is 10, your depth increases by 2X10=20`() {
            val startingPosition = SubLocationWithAim(horizontalPosition = 13, depth = 40, aim = 10)
            val result = startingPosition.applyCommand(Command(direction = Direction.FORWARD, units = 2))
            assertEquals(SubLocationWithAim(horizontalPosition = 15, depth = 60, aim = 10), result)
        }
    }
}