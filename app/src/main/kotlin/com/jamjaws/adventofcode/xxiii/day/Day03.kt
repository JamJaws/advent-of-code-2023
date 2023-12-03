package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput

class Day03 {

    fun part1(text: List<String>): Int {
        val numbers = text.map(Regex("\\d+")::findAll)
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

        val coordinates = text.map(Regex("[^.\\d]")::findAll)
            .flatMapIndexed { index, match ->
                match.flatMap(MatchResult::groups)
                    .filterNotNull()
                    .map(MatchGroup::range)
                    .map(IntRange::start)
                    .map { getAdjacentCoordinates(Pair(index, it)) }
                    .toList()
            }.flatten()

        return numbers.filter { it.coordinates.any(coordinates::contains) }
            .map(Numbers::value)
            .sum()
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
}
