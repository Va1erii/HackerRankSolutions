package problemSolving

/**
 * There will be two arrays of integers. Determine all integers that satisfy the following two conditions:
 * 1. The elements of the first array are all factors of the integer being considered
 * 2. The integer being considered is a factor of all elements of the second array
 *
 * These numbers are referred to as being between the two arrays. Determine how many such numbers exist.
 * Example
 * a = [2, 6]
 * b = [24, 36]
 *
 * There are two numbers between the arrays: 6 and 12.
 * 6 % 2 = 0
 * 6 % 6 = 0
 * 24 % 6 = 0
 * 36 % 6 = 0
 *
 * 12 % 2 = 0
 * 12 % 6 = 0
 * 24 % 12 = 0
 * 36 % 12 = 0
 *
 * Returns
 * int: the number of integers that are between the sets
 */

class Solution {
    fun getTotalX(a: Array<Int>, b: Array<Int>): Int {
        val gcd = gcd(b)
        val lcm = lcm(a)
        var count = 0
        for (i in lcm..gcd step lcm) {
            if (gcd.toLong() % i == 0L) count++
        }
        return count
    }

    /**
     * Find the greatest common divisor
     */
    fun gcd(array: Array<Int>): Int {
        var result = array[0]
        for (i in 1 until array.size) {
            result = gcd(result, array[i])
        }
        return result
    }

    fun gcd(a: Int, b: Int): Int {
        if (b % a == 0) return a
        val upper = a
        val lower = b % a
        return gcd(lower, upper)
    }

    /**
     * Find the least common multiply
     */
    fun lcm(array: Array<Int>): Long {
        var result: Long = array[0].toLong()
        for (i in 1 until array.size) {
            result = lcm(result.toInt(), array[i])
        }
        return result
    }

    fun lcm(a: Int, b: Int): Long {
        return a.toLong() * b / gcd(a, b)
    }
}


fun main() {
    // In this case the lcm is higher than integer
    val a = arrayOf(100, 99, 98, 97, 96, 95, 94, 93, 92, 91)
    val b = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    println(Solution().getTotalX(a, b))
}