package solutions.nilsen.ssn.app

object Checksum {
    private val k1_weights = arrayOf(3, 7, 6, 1, 8, 9, 4, 5, 2)
    private val k2_weights = arrayOf(5, 4, 3, 2, 7, 6, 5, 4, 3, 2)

    fun calculate(input: String): String? {
        if (!Regex("^[0-9]{9}$").matches(input)) {
            throw IllegalArgumentException("Invalid input: ${input}")
        }

        val sum1 = k1_weights.foldIndexed(0) { index, sum, weight ->
            sum + (weight * input[index].toString().toInt())
        }
        val k1Sum = 11 - (sum1 % 11)
        val k1 = if (k1Sum != 11) k1Sum else 0

        val sum2 = k2_weights.foldIndexed(0) { index, sum, weight ->
            sum + (weight * (input + k1)[index].toString().toInt())
        }
        val k2Sum = 11 - (sum2 % 11)
        val k2 = if (k2Sum != 11) k2Sum else 0

        if (k1 == 10 || k2 == 10) {
            return null
        }

        return "${k1}${k2}"
    }

    fun validate(input: String): Boolean {
        if (!Regex("^[0-9]{11}$").matches(input)) {
            throw IllegalArgumentException("Invalid input: ${input}")
        }

        val suffix = calculate(input.substring(0..8))
        return suffix != null && input.endsWith(suffix)
    }

}