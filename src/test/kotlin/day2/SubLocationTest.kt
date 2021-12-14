package day2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

internal class SubLocationTest {
    @Nested
    inner class ApplyCommand {
        @Test
        fun `forward 5 adds 5 to your horizontal position`() {
            val startingPosition = SubLocation(horizontalPosition = 0, depth = 0)
            val result = startingPosition.applyCommand(Command(direction = Direction.FORWARD, units = 5))
            assertEquals(SubLocation(horizontalPosition = 5, depth = 0), result)
        }

        @Test
        fun `down 5 adds 5 to your depth`() {
            val startingPosition = SubLocation(horizontalPosition = 5, depth = 0)
            val result = startingPosition.applyCommand(Command(direction = Direction.DOWN, units = 5))
            assertEquals(SubLocation(horizontalPosition = 5, depth = 5), result)
        }

        @Test
        fun `forward 8 adds 8 to your horizontal position`() {
            val startingPosition = SubLocation(horizontalPosition = 5, depth = 5)
            val result = startingPosition.applyCommand(Command(direction = Direction.FORWARD, units = 8))
            assertEquals(SubLocation(horizontalPosition = 13, depth = 5), result)
        }

        @Test
        fun `up 3 decreases your depth by 3`() {
            val startingPosition = SubLocation(horizontalPosition = 13, depth = 5)
            val result = startingPosition.applyCommand(Command(direction = Direction.UP, units = 3))
            assertEquals(SubLocation(horizontalPosition = 13, depth = 2), result)
        }

        @Test
        fun `down 8 adds 8 to your depth`() {
            val startingPosition = SubLocation(horizontalPosition = 13, depth = 2)
            val result = startingPosition.applyCommand(Command(direction = Direction.DOWN, units = 8))
            assertEquals(SubLocation(horizontalPosition = 13, depth = 10), result)
        }

        @Test
        fun `forward 2 adds 2 to your horizontal position`() {
            val startingPosition = SubLocation(horizontalPosition = 13, depth = 10)
            val result = startingPosition.applyCommand(Command(direction = Direction.FORWARD, units = 2))
            assertEquals(SubLocation(horizontalPosition = 15, depth = 10), result)
        }
    }
}