package problemSolving

import kotlin.math.max

/**
 * Given a set of distinct integers, print the size of a maximal subset of S where the sum of any 2 numbers
 * in S' is not evenly divisible by k.
 * For example, the array S = [19, 10, 12, 10, 24, 25, 22] and k = 4. One of the arrays that can be created is S'[0] = [10, 12, 25].
 * Another is S'[1] = [19, 22, 24]. After testing all permutations, the maximum length solution array has 3 elements.
 *
 * Function Description
 * Complete the nonDivisibleSubset function in the editor below. It should return an integer representing
 * the length of the longest subset of S meeting the criteria.
 *
 * nonDivisibleSubset has the following parameter(s):
 * - S: an array of integers
 * - k: an integer
 **/

fun nonDivisibleSubset(k: Int, s: Array<Int>): Int {
    // In maths. if (a + b) % k = 0 => then ((a % k) + (b % k)) % k = 0
    // Example: (5 + 7) % 6 = 0 => then (5 % 6) + (7 % 6) > (5 + 1) % 6 = 0

    // Solution: Find remainder of each element in the array.
    // then, choose max element from the pair which together can able to be divided by k. If one pair is "i" then other pair will be "k-i"
    // For example: S = {2, 3, 7, 8, 12} and k = 5.
    // Now we have 3 numbers whose remainder 2 => ( 2 % 5 = 2, 7 % 5 = 2, 12 % 5 = 2)
    // and also we have 2 numbers whose remainder 3 => (3, 8)
    // Right now we have to choose one of the element from that pair (3, 2) (where 3 > numbers 2, 7, 12 && 2 > numbers 3, 8)
    // Because of the problem, we will choose the max which is 3.
    val remainderArr = IntArray(k)

    // find remainder of each element in the array S
    // For example k = 4, S = {0, 5, 7, 10} => remainderArr will be: {0, 1, 1, 1}
    // where each index represents remainder. For example remainderArr[2] = 1 means
    // that there is 1 number whose remainder 2 after divided 4. (10 % 4 = 2)
    for (each in s) {
        remainderArr[each % k]++
    }

    // After getting each remainder, index 0 (actually remainder 0) is a special case
    // Think of it like this:
    //      1. There will be no element such as k - 0 = k. (remainderArr[k] will give us ArrayIndexOutOfBoundsException)
    //      2. If there are 2 elements in remainderArr[0], we have to choose only 1, otherwise, we can sum up 2 or more
    //         zeros, then non-sub divisible set could be divisible by k.
    val zeroRemainder = remainderArr[0]

    // That's why, our initial subset size is 1, if there is a zero remainder,
    //                                           otherwise it is 0
    var maxNumberOfDivisibleSet = if (zeroRemainder > 0) 1 else 0

    // Another thing is that pair which is itself. That's means, let's say k = 4, therefore pair of remainderArr[2]
    // will also be remainderArr[2]( i = 2 then, k - i = 2). Thus, we have to choose only 1 element from that pair (or we should increment
    // the result number just 1)
    // if condition "i != k - i" will handle this situation.
    for (i in 1..k / 2) {
        if (i != k - i)
            maxNumberOfDivisibleSet += max(remainderArr[i], remainderArr[k - i])
        else
            maxNumberOfDivisibleSet++
    }
    return maxNumberOfDivisibleSet
}

fun main() {
    val a1 = arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val k1 = 4
    val a2 = arrayOf(278, 576, 496, 727, 410, 124, 338, 149, 209, 702, 282, 718, 771, 575, 436)
    val k2 = 7

    println(nonDivisibleSubset(k1, a1)) // 5
    println(nonDivisibleSubset(k2, a2)) // 11
}