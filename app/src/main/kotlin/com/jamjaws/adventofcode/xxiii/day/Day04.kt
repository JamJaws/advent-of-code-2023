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

    fun part2(text: List<String>): Int {
        val scratchcards = text.map { line ->
            val cardId = Regex("\\d+").find(line)!!.value.toInt()
            val matches = line.substringAfter(":").split('|')
                .map(Regex("\\d+")::findAll).map { it.flatMap(MatchResult::groupValues).toList() }
                .let { (winningNumbers, elfNumbers) -> elfNumbers.intersect(winningNumbers) }
            Scratchcard(cardId, (cardId + 1..(cardId + matches.size)).map { it })
        }
        return scratchcards.sumOf { instances(it, scratchcards) }
    }

    private fun instances(scratchcard: Scratchcard, scratchcards: List<Scratchcard>): Int =
        scratchcards.filter { item -> item.copies.contains(scratchcard.card) }
            .map { item -> instances(item, scratchcards) }
            .takeIf(List<Int>::isNotEmpty)
            ?.sum()
            ?.let(1::plus)
            ?: 1
}

data class Scratchcard(val card: Int, val copies: List<Int>)

fun main() {
    val answer1 = Day04().part1(readInput("Day04"))
    println(answer1)
    val answer2 = Day04().part2(readInput("Day04"))
    println(answer2)
}
