package problemSolving

/**
 * Given the time in numerals we may convert it into words, as shown below:
 * 5:00 -> five o'clock
 * 5:01 -> one minute past five
 * 5:10 -> ten minutes past five
 * 5:15 -> quarter past five
 * 5:30 -> half past five
 * 5:40 -> twenty minutes to six
 * 5:45 -> quarter to six
 * 5:47 -> thirteen minutes to six
 * 5:28 -> twenty eight minutes past five
 *
 * At minutes = 0, use o' clock.
 * For 1 <= minutes <= 30, use past
 * For 30 < minutes use to.
 * Note the space between the apostrophe and clock in o' clock.
 * Write a program which prints the time in words for the input given in the format described.
 */
val minutes: Map<Int, String> = mapOf(
    0 to "o' clock",
    1 to "one minute",
    2 to "two minutes",
    3 to "three minutes",
    4 to "four minutes",
    5 to "five minutes",
    6 to "six minutes",
    7 to "seven minutes",
    8 to "eight minutes",
    9 to "nine minutes",
    10 to "ten minutes",
    11 to "eleven minutes",
    12 to "twelve minutes",
    13 to "thirteen minutes",
    14 to "fourteen minutes",
    15 to "quarter",
    16 to "sixteen minutes",
    17 to "seventeen minutes",
    18 to "eighteen minutes",
    19 to "nineteen minutes",
    20 to "twenty minutes",
    21 to "twenty one minutes",
    22 to "twenty two minutes",
    23 to "twenty three minutes",
    24 to "twenty four minutes",
    25 to "twenty five minutes",
    26 to "twenty six minutes",
    27 to "twenty seven minutes",
    28 to "twenty eight minutes",
    29 to "twenty nine minutes",
    30 to "half"
)

val hours: Map<Int, String> = mapOf(
    1 to "one",
    2 to "two",
    3 to "three",
    4 to "four",
    5 to "five",
    6 to "six",
    7 to "seven",
    8 to "eight",
    9 to "nine",
    10 to "ten",
    11 to "eleven",
    12 to "twelve"
)

fun timeInWords(h: Int, m: Int): String {
    return if (m == 0) {
        "${hours[h]} o' clock"
    } else if (m in 1..30) {
        "${minutes[m]} past ${hours[h]}"
    } else {
        if (h + 1 == 13) {
            "${minutes[m]} past ${hours[1]}"
        } else {
            "${minutes[60 - m]} to ${hours[h + 1]}"
        }
    }
}