private const val REGEX_ERROR = "No numbers attached to command"

private const val COMMAND_FORWARD = "forward "
private const val COMMAND_DOWN = "down "
private const val COMMAND_UP = "up "

fun main() {
    fun part1(input: List<String>) : Int {
        val regex = """(\d)""".toRegex()
        var horizontalPosition = 0
        var depthPosition = 0
        input.forEach { command ->
            when {
                command.startsWith(COMMAND_FORWARD) -> horizontalPosition += regex.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
                command.startsWith(COMMAND_DOWN) ->  depthPosition += regex.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
                command.startsWith(COMMAND_UP) ->  depthPosition -= regex.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
            }
        }
        return horizontalPosition * depthPosition
    }

    fun part2(input: List<String>): Int {
        val regex = """(\d)""".toRegex()
        var horizontalPosition = 0
        var depthPosition = 0
        var aim = 0
        input.forEach { command ->
            when {
                command.startsWith(COMMAND_FORWARD) -> {
                    horizontalPosition += regex.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
                    depthPosition += aim * (regex.find(command)?.value?.toInt() ?: error(REGEX_ERROR))
                }
                command.startsWith(COMMAND_DOWN) ->  aim += regex.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
                command.startsWith(COMMAND_UP) ->  aim -= regex.find(command)?.value?.toInt() ?: error(REGEX_ERROR)
            }
        }
        return horizontalPosition * depthPosition
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}