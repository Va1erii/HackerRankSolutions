package problemSolving

/**
 * An arcade game player wants to climb to the top of the leaderboard and track their ranking.
 * The game uses Dense Ranking, so its leaderboard works like this:
 * - The player with the highest score is ranked number  on the leaderboard.
 * - Players who have equal scores receive the same ranking number, and the next player(s) receive the immediately
 *   following ranking number.
 *
 *   Example
 *   ranked = [100, 90, 90, 80]
 *   player = [70, 80, 105]
 *   The ranked players will have ranks 1, 2, 2, and 3, respectively. If the player's scores are 70, 80 and 105, their
 *   rankings after each game are 4, 3 and 1. Return [4, 3, 1].
 *
 *   Function Description
 *   Complete the climbingLeaderboard function in the editor below.
 *   climbingLeaderboard has the following parameter(s):
 *   - int ranked[n]: the leaderboard scores
 *   - int player[m]: the player's scores
 *
 *   Returns
 *   - int[m]: the player's rank after each new score
 */

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