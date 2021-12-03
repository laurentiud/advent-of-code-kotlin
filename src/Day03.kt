private const val ZERO = '0'
private const val ONE = '1'

fun main() {
    fun part1(input: List<String>) : Int {
        var gammaRate = ""
        var epsilonRate = ""

        (0 until input.first().length).forEach { position ->
           val zero = input.map { it[position] }.count { it == ZERO }
           val one = input.map { it[position] }.count { it == ONE }
            if (zero > one) {
                gammaRate = gammaRate.plus(ZERO)
                epsilonRate = epsilonRate.plus(ONE)
            } else {
                gammaRate = gammaRate.plus(ONE)
                epsilonRate = epsilonRate.plus(ZERO)
            }
        }

        return getDecimalFromLongBinary(gammaRate.toLong()) * getDecimalFromLongBinary(epsilonRate.toLong())
    }

    fun part2(input: List<String>): Int {
        val oxygenSelector: (List<String>, List<String>) -> List<String> = { zeroes, ones ->
            when {
                ones.size >= zeroes.size -> ones
                else -> zeroes
            }
        }
        val co2Selector: (List<String>, List<String>) -> List<String> = { zeroes, ones ->
            when {
                ones.size < zeroes.size -> ones
                else -> zeroes
            }
        }
        return getRating(input, 0, oxygenSelector) * getRating(input, 0, co2Selector)
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

private fun getRating(elements: List<String>, position: Int, elementsSelector: (List<String>, List<String>) -> List<String>) : Int {
    return if (elements.size == 1) getDecimalFromLongBinary(elements.first().toLong())
    else {
        val zeroElements = elements.filter { it[position] == ZERO }
        val oneElements = elements.filter { it[position] == ONE }
        return getRating(elementsSelector.invoke(zeroElements, oneElements), position.inc(), elementsSelector)
    }
}


private fun getDecimalFromLongBinary(binaryNumber: Long): Int {
    var binaryNumber = binaryNumber
    var decimalNo = 0
    var power = 0

    while (binaryNumber > 0) {
        val r = binaryNumber % 10
        decimalNo = (decimalNo + r * Math.pow(2.0, power.toDouble())).toInt()
        binaryNumber /= 10
        power++
    }
    return decimalNo
}