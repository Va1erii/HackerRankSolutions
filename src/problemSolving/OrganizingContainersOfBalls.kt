package problemSolving

import java.util.*


/**
 * David has several containers, each with a number of balls in it. He has just enough containers to sort each
 * type of ball he has into its own container. David wants to sort the balls using his sort method.
 * As an example, David has n = 2 containers and 2 different types of balls, both of which are numbered from 0 to n - 1 = 1.
 * The distribution of ball types per container are described by an n x n matrix of integers, M(container)(type).
 * For example, consider the following diagram for M = [[1,4], [2,3]]
 */

fun organizingContainers(container: Array<IntArray>): String {
    //array to count amount of each ball, it's position is a type and value the amount
    val ballsCount = IntArray(container[0].size)
    //array to count how many balls are currently in each basket
    val basketCapacity = IntArray(container[0].size)
    //iterate through every array and then every value in it
    for (i in container.indices) {
        for (j in container.indices) {
            basketCapacity[i] += container[i][j]
            ballsCount[j] += container[i][j]
        }
    }
    Arrays.sort(ballsCount)
    Arrays.sort(basketCapacity)
    return if (ballsCount.contentEquals(basketCapacity)) {
        "Possible"
    } else {
        "Impossible"
    }
}