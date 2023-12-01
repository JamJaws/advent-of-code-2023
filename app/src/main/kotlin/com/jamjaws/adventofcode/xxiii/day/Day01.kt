package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput

class Day01 {

    private val spelledDigits = mapOf(
        "one" to '1',
        "two" to '2',
        "three" to '3',
        "four" to '4',
        "five" to '5',
        "six" to '6',
        "seven" to '7',
        "eight" to '8',
        "nine" to '9',
    )

    fun part1(text: List<String>): Int =
        text.map { line -> line.filter(Char::isDigit) }
            .map { digits -> "${digits.first()}${digits.last()}" }
            .map(String::toInt)
            .sum()

    fun part2(text: List<String>): Int =
        text.map(::replaceSpelledDigits).let(::part1)

    private fun replaceSpelledDigits(text: String): String =
        text.indices.map(text::substring)
            .map { value ->
                spelledDigits.entries.find { (spelledDigit, _) -> value.startsWith(spelledDigit) }
                    ?.value
                    ?: value.first()
            }.joinToString("")

}

fun main() {
    val answer1 = Day01().part1(readInput("Day01"))
    println(answer1)
    val answer2 = Day01().part2(readInput("Day01"))
    println(answer2)
}
