package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput

class Day09 {

    fun part1(text: List<String>): Int =
        text.asSequence()
            .map { line -> line.split(' ').map(String::toInt) }
            .map { values -> getDifferenceSequences(values) }
            .map { differences -> differences.reversed().map(List<Int>::last) }
            .sumOf { lasts -> lasts.sum() }

    fun part2(text: List<String>): Int =
        text.asSequence()
            .map { line -> line.split(' ').map(String::toInt) }
            .map { values -> getDifferenceSequences(values) }
            .map { differences -> differences.reversed() }
            .map { differences -> differences.map(List<Int>::first) }
            .map { firsts -> firsts.reduce { acc, value -> value - acc } }
            .sum()

    private tailrec fun getDifferenceSequences(
        values: List<Int>,
        accumulator: List<List<Int>> = emptyList(),
    ): List<List<Int>> =
        if (values.all { it == 0 }) {
            accumulator + listOf(values)
        } else {
            val diffValues = values.windowed(2, 1) { (a, b) -> b - a }
            getDifferenceSequences(diffValues, accumulator + listOf(values))
        }

}


fun main() {
    val answer1 = Day09().part1(readInput("Day09"))
    println(answer1)
    val answer2 = Day09().part2(readInput("Day09"))
    println(answer2)
}
