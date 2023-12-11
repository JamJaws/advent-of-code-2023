package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import kotlin.math.abs


class Day11 {

    fun part1(text: List<String>): Long {
        val expandingRows = text.getExpansionRowIndexes()
        val expandingColumns = text.transpose().getExpansionRowIndexes()
        val galaxyCombinations = text.getGalaxies().combinations().toList()
        return galaxyCombinations.sumOf { (a, b) -> getGalaxyPathLength(a, b, expandingColumns, expandingRows) }
    }

    fun part2(text: List<String>): Long {
        val rows = text.getExpansionRowIndexes()
        val columns = text.transpose().getExpansionRowIndexes()
        val galaxyCombinations = text.getGalaxies().combinations().toList()
        return galaxyCombinations.sumOf { (a, b) -> getGalaxyPathLength(a, b, columns, rows, 1000000) }
    }


    private fun List<String>.getExpansionRowIndexes() =
        mapIndexedNotNull { index, line -> line.takeIf { it.all('.'::equals) }?.let { index } }

    private fun List<String>.transpose(): List<String> =
        first().indices.map { x -> indices.map { y -> this[y][x] } }.map(List<Char>::toCharArray).map(::String)

    private fun List<String>.getGalaxies() =
        flatMapIndexed { y: Int, line: String ->
            line.mapIndexedNotNull { x, char -> char.takeIf('#'::equals)?.let { Galaxy(1, x, y) } }
        }.mapIndexed { index, pair -> pair.copy(id = index + 1) }

    private fun <T> List<T>.combinations() =
        asSequence()
            .flatMapIndexed { index: Int, a: T ->
                drop(index + 1).map { b -> a to b }
            }

    private fun getGalaxyPathLength(
        galaxy1: Galaxy,
        galaxy2: Galaxy,
        columns: List<Int>,
        rows: List<Int>,
        expansionMultiplier: Long = 2L,
    ): Long {
        val expandedX = (minOf(galaxy1.x, galaxy2.x)..maxOf(galaxy1.x, galaxy2.x))
            .count { it in columns }
            .toLong()
            .times(expansionMultiplier.dec())
        val expandedY = (minOf(galaxy1.y, galaxy2.y)..maxOf(galaxy1.y, galaxy2.y))
            .count { it in rows }
            .toLong()
            .times(expansionMultiplier.dec())
        val xDiff = abs(galaxy1.x - galaxy2.x).toLong()
        val yDiff = abs(galaxy1.y - galaxy2.y).toLong()
        return xDiff + yDiff + expandedX + expandedY
    }

    data class Galaxy(val id: Int, val x: Int, val y: Int)

}


fun main() {
    val answer1 = Day11().part1(readInput("Day11"))
    println(answer1)
    val answer2 = Day11().part2(readInput("Day11"))
    println(answer2)
}
