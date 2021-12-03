private const val REGEX_ERROR = "No numbers attached to command"

private const val COMMAND_FORWARD = "forward "
private const val COMMAND_DOWN = "down "
private const val COMMAND_UP = "up "

private val REGEX = """(\d)""".toRegex()

fun main() {
    fun part1(input: List<String>) : Int {
        var horizontalPosition = 0
        var depthPosition = 0
        input.forEach { command ->
            when {
                command.startsWith(COMMAND_FORWARD) -> horizontalPosition += REGEX.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
                command.startsWith(COMMAND_DOWN) ->  depthPosition += REGEX.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
                command.startsWith(COMMAND_UP) ->  depthPosition -= REGEX.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
            }
        }
        return horizontalPosition * depthPosition
    }

    fun part2(input: List<String>): Int {
        var horizontalPosition = 0
        var depthPosition = 0
        var aim = 0
        input.forEach { command ->
            when {
                command.startsWith(COMMAND_FORWARD) -> {
                    horizontalPosition += REGEX.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
                    depthPosition += aim * (REGEX.find(command)?.value?.toInt() ?: error(REGEX_ERROR))
                }
                command.startsWith(COMMAND_DOWN) ->  aim += REGEX.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
                command.startsWith(COMMAND_UP) ->  aim -= REGEX.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
            }
        }
        return horizontalPosition * depthPosition
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}