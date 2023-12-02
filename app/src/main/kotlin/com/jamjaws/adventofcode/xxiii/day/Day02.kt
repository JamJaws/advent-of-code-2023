package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput

class Day02 {

    fun part1(text: List<String>): Int = text.map(::parseRow).let(::getPossibleGames).map(Game::id).sum()

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
        game.sets.all { set -> set.red <= MAX_RED && set.green <= MAX_GREEN && set.blue <= MAX_BLUE }
    }

    private fun String.getNumberOfCubes(color: String): Int =
        Regex("(\\d+) $color").find(this)?.groupValues?.get(1)?.toInt() ?: 0

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
    val red: Int = 0,
    val green: Int = 0,
    val blue: Int = 0,
)

fun main() {
    val answer1 = Day02().part1(readInput("Day02"))
    println(answer1)
}
