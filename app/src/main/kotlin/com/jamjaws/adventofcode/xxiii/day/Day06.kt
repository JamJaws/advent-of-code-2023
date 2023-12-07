package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput

class Day06 {

    fun part1(text: List<String>): Long {
        val races = text.fold(listOf<Race>()) { acc, line ->
            line.substringAfter(":")
                .split(' ')
                .filter(String::isNotBlank)
                .map(String::toLong)
                .let { values ->
                    when (acc.isEmpty()) {
                        true -> acc + values.map { Race(it, 0) }
                        false -> acc.mapIndexed { index, race -> race.copy(distance = values[index]) }
                    }
                }
        }
        return races
            .map { race -> howManyWaysToBeatRecord(race) }
            .fold(0) { acc, waysToBeatRecord ->
                when {
                    (acc == 0) -> waysToBeatRecord
                    else -> acc * waysToBeatRecord
                }
            }
            .toLong()
    }

    fun part2(text: List<String>): Int =
        text.map { line ->
            line.substringAfter(":")
                .filter(Char::isDigit)
                .let(String::toLong)
        }.let { (time, distance) -> Race(time, distance) }
            .let(::howManyWaysToBeatRecord)

    private fun howManyWaysToBeatRecord(race: Race) =
        (1 until race.time)
            .map { it * (race.time - it) }
            .count { it > race.distance }
}

data class Race(val time: Long, val distance: Long)

fun main() {
    val answer1 = Day06().part1(readInput("Day06"))
    println(answer1)
    val answer2 = Day06().part2(readInput("Day06"))
    println(answer2)
}
