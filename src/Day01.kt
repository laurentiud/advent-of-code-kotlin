fun main() {
    fun part1(input: List<String>) : Int {
        return countIncreases(input.map { it.toInt() })
    }

    fun part2(input: List<String>): Int {
        val movingSums = input.mapIndexed { index, element ->
            if (index == 0 || index == input.size - 1) 0
            else input[index - 1].toInt() + element.toInt() + input[index + 1].toInt()
        }
        return countIncreases(movingSums.subList(1, movingSums.size - 1))
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}

private fun countIncreases(elements : List<Int>) : Int {
    return elements.mapIndexed { index, element ->
        if (index > 0 && element > elements[index - 1])
            1
        else 0
    }.sum()
}