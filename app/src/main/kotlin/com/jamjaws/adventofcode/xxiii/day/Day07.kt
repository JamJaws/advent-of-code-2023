package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput

class Day07 {

    companion object {
        private val CARD_OPTIONS = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
    }

    fun part1(text: List<String>): Int =
        text.asSequence()
            .map {
                val (cards, bid) = it.split(' ')
                Hand(cards, getHandType(cards), bid.toInt())
            }.sortedWith(compareBy<Hand> { it.type.ordinal }
                .thenBy { CARD_OPTIONS.indexOf(it.cards.first()) }
                .thenBy { CARD_OPTIONS.indexOf(it.cards[1]) }
                .thenBy { CARD_OPTIONS.indexOf(it.cards[2]) }
                .thenBy { CARD_OPTIONS.indexOf(it.cards[3]) }
                .thenBy { CARD_OPTIONS.indexOf(it.cards[4]) }
            ).map(Hand::bid)
            .mapIndexed { index, bid -> bid * (text.size - index) }
            .sum()

    fun part2(text: List<String>): Int = TODO("2")

    private fun getHandType(cards: String): HandType {
        val distinctCards = cards.toSet()
        return when {
            distinctCards.size == 1 -> HandType.FiveOfAKind
            distinctCards.size == 2 && distinctCards.any { distinctCard -> cards.count { it == distinctCard } == 4 } -> HandType.FourOfAKind
            distinctCards.size == 2 && distinctCards.any { distinctCard -> cards.count { it == distinctCard } == 3 } -> HandType.FullHouse
            distinctCards.size == 3 && distinctCards.any { distinctCard -> cards.count { it == distinctCard } == 3 } -> HandType.ThreeOfAKind
            distinctCards.size == 3 && distinctCards.any { distinctCard -> cards.count { it == distinctCard } == 2 } -> HandType.TwoPair
            distinctCards.size == 4 -> HandType.OnePair
            else -> HandType.HighCard
        }
    }

}

enum class HandType {
    FiveOfAKind,
    FourOfAKind,
    FullHouse,
    ThreeOfAKind,
    TwoPair,
    OnePair,
    HighCard,
}

data class Hand(val cards: String, val type: HandType, val bid: Int)


fun main() {
    val answer1 = Day07().part1(readInput("Day07"))
    println(answer1)
//    val answer2 = Day07().part2(readInput("Day07"))
//    println(answer2)
}
