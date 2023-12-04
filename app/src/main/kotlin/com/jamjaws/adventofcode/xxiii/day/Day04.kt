package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import kotlin.collections.Set
import kotlin.math.pow

class Day04 {

    fun part1(text: List<String>): Int =
        text.asSequence()
            .map { it.substringAfter(":") }
            .map { it.split('|') }
            .map { it.map(Regex("\\d+")::findAll).map { it.flatMap(MatchResult::groupValues).toList() } }
            .map { (winningNumbers, elfNumbers) -> elfNumbers.intersect(winningNumbers) }
            .map(Set<String>::size)
            .map { 2.0.pow(it - 1).toInt() }
            .sum()
}

fun main() {
    val answer1 = Day04().part1(readInput("example/Day04"))
    println(answer1)
}
