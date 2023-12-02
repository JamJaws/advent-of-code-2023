package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput

class Day02 {

    fun part1(text: List<String>): Int = text.map(::parseRow).let(::getPossibleGames).map(Game::id).sum()

    fun part2(text: List<String>): Int =
        text.map(::parseRow)
            .map { game ->
                listOf(
                    game.sets.mapNotNull(Set::red).max(),
                    game.sets.mapNotNull(Set::green).max(),
                    game.sets.mapNotNull(Set::blue).max(),
                )
            }
            .sumOf { (red, green, blue) -> red * green * blue }

    private fun parseRow(line: String): Game {
        val id = line.removePrefix("Game ").split(":").first().toInt()
        val sets = line.split(";").map {
            Set(
                red = it.getNumberOfCubes("red"),
                green = it.getNumberOfCubes("green"),
                blue = it.getNumberOfCubes("blue"),
            )
        }
        return Game(id, sets)
    }

    private fun getPossibleGames(games: List<Game>): List<Game> = games.filter { game ->
        game.sets.all { set ->
            (set.red ?: 0) <= MAX_RED && (set.green ?: 0) <= MAX_GREEN && (set.blue ?: 0) <= MAX_BLUE
        }
    }

    private fun String.getNumberOfCubes(color: String): Int? =
        Regex("(\\d+) $color").find(this)?.groupValues?.get(1)?.toInt()

    companion object {
        private const val MAX_RED = 12
        private const val MAX_GREEN = 13
        private const val MAX_BLUE = 14
    }
}

data class Game(
    val id: Int,
    val sets: List<Set>,
)

data class Set(
    val red: Int? = null,
    val green: Int? = null,
    val blue: Int? = null,
)

fun main() {
    val answer1 = Day02().part1(readInput("Day02"))
    println(answer1)
    val answer2 = Day02().part2(readInput("Day02"))
    println(answer2)
}
