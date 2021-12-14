package day2

data class SubLocationWithAim(
    val horizontalPosition: Int,
    val depth: Int,
    val aim: Int
) {
    fun applyCommand(command: Command): SubLocationWithAim =
        when(command.direction) {
            Direction.FORWARD -> copy(horizontalPosition = horizontalPosition + command.units, depth = depth + command.units * aim)
            Direction.DOWN -> copy(aim = aim + command.units)
            Direction.UP -> copy(aim = aim - command.units)
        }
}
