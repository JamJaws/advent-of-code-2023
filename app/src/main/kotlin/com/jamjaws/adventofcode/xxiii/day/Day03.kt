package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput

class Day03 {

    fun part1(text: List<String>): Int {
        val numbers = getNumbers(text)
        val coordinates = getCoordinates(text, "[^.\\d]").flatMap(::getAdjacentCoordinates)

        return numbers.filter { it.coordinates.any(coordinates::contains) }
            .map(Numbers::value)
            .sum()
    }

    fun part2(text: List<String>): Int {
        val numbers = getNumbers(text)
        val coordinates = getCoordinates(text, "\\*").map(::getAdjacentCoordinates)

        return coordinates.map { coordinate ->
            numbers.filter { number -> number.coordinates.any(coordinate::contains) }
        }.filter { it.size == 2 }
            .map { it.map(Numbers::value) }
            .sumOf { (first, second) -> first * second }
    }

    private fun getCoordinates(text: List<String>, pattern: String) =
        text.map(Regex(pattern)::findAll)
            .flatMapIndexed { index, match ->
                match.flatMap(MatchResult::groups)
                    .filterNotNull()
                    .map(MatchGroup::range)
                    .map(IntRange::start)
                    .map { Pair(index, it) }
                    .toList()
            }

    private fun getNumbers(text: List<String>) =
        text.map(Regex("\\d+")::findAll)
            .flatMapIndexed { index, match ->
                match.flatMap(MatchResult::groups)
                    .filterNotNull()
                    .map { group ->
                        Numbers(
                            group.value.toInt(),
                            group.range.map { Pair(index, it) }
                        )
                    }.toList()
            }

    private fun getAdjacentCoordinates(coordinate: Pair<Int, Int>): List<Pair<Int, Int>> {
        val (y, x) = coordinate
        return (-1..1).flatMap { yDelta ->
            (-1..1).map { xDelta ->
                Pair(y + yDelta, x + xDelta)
            }
        }
    }
}

data class Numbers(
    val value: Int, val coordinates: List<Pair<Int, Int>>,
)

fun main() {
    val answer1 = Day03().part1(readInput("Day03"))
    println(answer1)
    val answer2 = Day03().part2(readInput("Day03"))
    println(answer2)
}
