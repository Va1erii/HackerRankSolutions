package problemSolving

/**
 * You will be given a square chess board with one queen and a number of obstacles placed on it.
 * Determine how many squares the queen can attack. A queen is standing on an n x n chessboard.
 * The chess board's rows are numbered from 1 to n, going from bottom to top.
 * Its columns are numbered from 1 to n, going from left to right.
 * Each square is referenced by a tuple, (r, c), describing the row, r, and column, c, where the square is located.
 * The queen is standing at position (rq, rc). In a single move, she can attack any square in any of the eight
 * directions (left, right, up, down, and the four diagonals).
 *
 * Complete the queensAttack function in the editor below.
 * It should return an integer that describes the number of squares the queen can attack.
 */

fun queensAttack(boardSize: Int, k: Int, queenX: Int, queenY: Int, obstacles: Array<Array<Int>>): Int {
    var top = boardSize - queenY
    var right = boardSize - queenX
    var bottom = boardSize - (boardSize - queenY) - 1
    var left = boardSize - (boardSize - queenX) - 1
    var topRight = Math.min(top, right)
    var topLeft = Math.min(top, left)
    var bottomRight = Math.min(bottom, right)
    var bottomLeft = Math.min(bottom, left)

    obstacles.forEach {
        val obstacleX = it[0]
        val obstacleY = it[1]
        if (queenX == obstacleX && obstacleY > queenY) {
            top = Math.min(top, obstacleY - queenY - 1)
        }
        if (queenY == obstacleY && queenX < obstacleX) {
            right = Math.min(right, obstacleX - queenX - 1)
        }
        if (queenX == obstacleX && obstacleY < queenY) {
            bottom = Math.min(bottom, queenY - obstacleY - 1)
        }
        if (queenY == obstacleY && queenX > obstacleX) {
            left = Math.min(left, queenX - obstacleX - 1)
        }
        if (obstacleY > queenY && obstacleX > queenX && obstacleY - queenY == obstacleX - queenX) {
            topRight = Math.min(topRight, obstacleY - queenY - 1)
        }
        if (obstacleY > queenY && obstacleX < queenX && obstacleY - queenY == queenX - obstacleX) {
            topLeft = Math.min(topLeft, obstacleY - queenY - 1)
        }
        if (queenY > obstacleY && obstacleX > queenX && queenY - obstacleY == obstacleX - queenX) {
            bottomRight = Math.min(bottomRight, queenY - obstacleY - 1)
        }
        if (queenY > obstacleY && obstacleX < queenX && queenY - obstacleY == queenX - obstacleX) {
            bottomLeft = Math.min(bottomLeft, queenY - obstacleY - 1)
        }
    }
    return top + right + bottom + left + topRight + topLeft + bottomLeft + bottomRight
}

fun main() {
    val n = 5
    val k = 3
    val r_q = 4
    val c_q = 3
    val obstacles: Array<Array<Int>> = arrayOf(arrayOf(5, 5), arrayOf(4, 2), arrayOf(2, 3))

    println(queensAttack(n, k, r_q, c_q, obstacles))
}