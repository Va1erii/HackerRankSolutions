package problemSolving

/**
 * Madison, is a little girl who is fond of toys. Her friend Mason works in a toy manufacturing factory.
 * Mason has a 2D board A of size H x W with H rows and W columns. The board is divided into cells of size 1 x 1
 * with each cell indicated by it's coordinate (i, j). The cell (i, j) has an integer Aij written on it.
 * To create the toy Mason stacks Aij number of cubes of size 1 x 1 x 1 on the cell (i, j).
 * Given the description of the board showing the values of Aij and that the price of the toy is equal
 * to the 3d surface area find the price of the toy.
 */

fun surfaceArea(array: Array<Array<Int>>): Int {
    var tb = 0
    // top, bottom
    for (i in array.indices) {
        for (element in array[i]) {
            if (element != 0) tb += 1
        }
    }
    tb *= 2
    var lr = 0
    // left, right
    for (i in array[0].indices) {
        for (j in array.indices) {
            if (j == 0) lr += array[j][i]
            else {
                val diff = array[j - 1][i] - array[j][i]
                lr += Math.abs(diff)
                if (j == array.size - 1) {
                    lr += array[j][i]
                }
            }
        }
    }
    if (array.size == 1) lr *= 2
    var fb = 0
    // front, back
    for (i in array.indices) {
        for (j in array[i].indices) {
            if (j == 0) fb += array[i][j]
            else {
                val diff = array[i][j - 1] - array[i][j]
                fb += Math.abs(diff)
                if (j == array[i].size - 1) {
                    fb += array[i][j]
                }
            }
        }
    }
    if (array[0].size == 1) fb *= 2
    return fb + lr + tb
}

fun main() {
    val array = arrayOf(arrayOf(91, 80, 7, 41, 36, 11, 48, 57, 40, 43)) //1276
    println(surfaceArea(array))
}