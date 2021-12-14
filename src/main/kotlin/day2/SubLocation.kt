package day2

data class SubLocation(
    val horizontalPosition: Int,
    val depth: Int
) {
    fun applyCommand(command: Command): SubLocation =
        when(command.direction) {
            Direction.FORWARD -> copy(horizontalPosition = horizontalPosition + command.units)
            Direction.DOWN -> copy(depth = depth + command.units)
            Direction.UP -> copy(depth = depth - command.units)
        }
}
