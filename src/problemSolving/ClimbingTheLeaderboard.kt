package problemSolving

fun climbingLeaderboard(ranked: Array<Int>, player: Array<Int>): Array<Int> {
    var rankTemp: Int = ranked[0]
    val rankedArray = ArrayList<Int>()
    rankedArray.add(rankTemp)
    for (i in 1 until ranked.size) {
        if (rankTemp == ranked[i]) continue
        rankTemp = ranked[i]
        rankedArray.add(rankTemp)
    }
    var lastIndex = 0
    val minScore = rankedArray[rankedArray.size - 1]
    main@for (n in player.size - 1 downTo 0) {
        for(r in lastIndex until rankedArray.size) {
            if (player[n] < minScore) {
                player[n] = rankedArray.size + 1
                continue@main
            } else if (player[n] >= rankedArray[r]) {
                player[n] = r + 1
                lastIndex = r
                continue@main
            }
        }
    }
    return player
}