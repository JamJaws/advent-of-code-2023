package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import java.awt.Polygon


class Day10 {

    fun part1(text: List<String>): Int {
        val (xStart, yStart) = getStartCoordinate(text)
        val start = Pipe(xStart, yStart, PipeType.START)
        val loop = getPipeLoop(start, text)
        return loop.count() / 2
    }

    fun part2(text: List<String>): Int {
        val (xStart, yStart) = getStartCoordinate(text)
        val start = Pipe(xStart, yStart, PipeType.START)
        val loop = getPipeLoop(start, text)
        val polygon = Polygon(loop.map(Pipe::x).toIntArray(), loop.map(Pipe::y).toIntArray(), loop.size)
        val loopTiles = text.flatMapIndexed { y: Int, line: String -> line.indices.mapNotNull { x -> Pair(x, y) } }
            .filter { (x, y) -> polygon.intersects(x - 0.5, y - 0.5, 1.0, 1.0) }
        val enclosedByTheLoop = loopTiles.filterNot { tile -> loop.any { tile == it.coordinate } }
        return enclosedByTheLoop.size
    }

    private fun getPipeLoop(
        start: Pipe,
        text: List<String>
    ) = generateSequence(start) {
        it.getNext(text)
    }.takeWhile {
        it == start || it.pipeType != PipeType.START
    }.toList()

    private fun getStartCoordinate(text: List<String>): Pair<Int, Int> =
        text.indexOfFirst { line -> line.contains(PipeType.START.char) }
            .let { y -> text[y].indexOfFirst { it == PipeType.START.char } to y }

    enum class PipeType(val char: Char) {
        NS('|'),
        EW('-'),
        NE('L'),
        NW('J'),
        SW('7'),
        SE('F'),
        GROUND('.'),
        START('S');

        companion object {
            fun of(char: Char) = entries.first { it.char == char }

            val toNorth = listOf(NS, SE, SW)
            val toEast = listOf(EW, NW, SW)
            val toSouth = listOf(NS, NE, NW)
            val toWest = listOf(EW, NE, SE)
        }
    }

    class Pipe(val x: Int, val y: Int, val pipeType: PipeType, private val from: Pair<Int, Int>? = null) {

        constructor(coordinate: Pair<Int, Int>, sketch: List<String>, from: Pipe?) : this(
            coordinate.first,
            coordinate.second,
            PipeType.of(sketch[coordinate.second][coordinate.first]),
            from?.coordinate,
        )

        val coordinate get() = Pair(x, y)

        fun getNext(sketch: List<String>): Pipe =
            when (pipeType) {
                PipeType.NS -> sequenceOf(n, s).next(sketch)
                PipeType.EW -> sequenceOf(e, w).next(sketch)
                PipeType.NE -> sequenceOf(n, e).next(sketch)
                PipeType.NW -> sequenceOf(n, w).next(sketch)
                PipeType.SW -> sequenceOf(s, w).next(sketch)
                PipeType.SE -> sequenceOf(s, e).next(sketch)
                PipeType.START -> {
                    sequence {
                        yield(Pipe(n, sketch, this@Pipe).takeIf { it.pipeType in PipeType.toNorth })
                        yield(Pipe(e, sketch, this@Pipe).takeIf { it.pipeType in PipeType.toEast })
                        yield(Pipe(s, sketch, this@Pipe).takeIf { it.pipeType in PipeType.toSouth })
                        yield(Pipe(w, sketch, this@Pipe).takeIf { it.pipeType in PipeType.toWest })
                    }.filterNotNull()
                        .first()
                }

                PipeType.GROUND -> throw IllegalStateException("should never go here")
            }

        private fun Sequence<Pair<Int, Int>>.next(sketch: List<String>): Pipe =
            map { Pipe(it, sketch, this@Pipe) }.first { it.coordinate != from }

        private val n get() = Pair(x, y - 1)
        private val s get() = Pair(x, y + 1)
        private val e get() = Pair(x + 1, y)
        private val w get() = Pair(x - 1, y)
    }

}


fun main() {
    val answer1 = Day10().part1(readInput("Day10"))
    println(answer1)
    val answer2 = Day10().part2(readInput("Day10"))
    println(answer2)
}
