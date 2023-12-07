package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput

class Day07 {

    companion object {
        private val CARD_OPTIONS = listOf('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2')
        private val CARD_OPTIONS_JOKER = listOf('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J')
    }

    fun part1(text: List<String>): Int =
        text.asSequence()
            .map { parseHand(it, ::getHandType) }
            .sortedWith(getHandComparator(CARD_OPTIONS))
            .map(Hand::bid)
            .mapIndexed { index, bid -> bid * (text.size - index) }
            .sum()

    fun part2(text: List<String>): Int =
        text.asSequence()
            .map { parseHand(it, ::getHandTypeWithJoker) }
            .sortedWith(getHandComparator(CARD_OPTIONS_JOKER))
            .map(Hand::bid)
            .mapIndexed { index, bid -> bid * (text.size - index) }
            .sum()

    private fun parseHand(line: String, getHandType: (String) -> HandType): Hand {
        val (cards, bid) = line.split(' ')
        return Hand(cards, getHandType(cards), bid.toInt())
    }

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

    private fun getHandTypeWithJoker(cards: String): HandType {
        val distinctCardsMinusJ = cards.toSet() - 'J'
        val jokerCount = cards.count { it == 'J' }
        return when {
            distinctCardsMinusJ.size in 0..1 -> HandType.FiveOfAKind
            distinctCardsMinusJ.size == 2 && distinctCardsMinusJ.any { distinctCard -> jokerCount + cards.count { it == distinctCard } == 4 } -> HandType.FourOfAKind
            distinctCardsMinusJ.size == 2 && distinctCardsMinusJ.any { distinctCard -> jokerCount + cards.count { it == distinctCard } == 3 } -> HandType.FullHouse
            distinctCardsMinusJ.size == 3 && distinctCardsMinusJ.any { distinctCard -> jokerCount + cards.count { it == distinctCard } == 3 } -> HandType.ThreeOfAKind
            distinctCardsMinusJ.size == 3 && distinctCardsMinusJ.any { distinctCard -> jokerCount + cards.count { it == distinctCard } == 2 } -> HandType.TwoPair
            distinctCardsMinusJ.size == 4 -> HandType.OnePair
            else -> HandType.HighCard
        }
    }

    private fun getHandComparator(cardOptionsSorted: List<Char>) = compareBy<Hand> { it.type.ordinal }
        .thenBy { cardOptionsSorted.indexOf(it.cards.first()) }
        .thenBy { cardOptionsSorted.indexOf(it.cards[1]) }
        .thenBy { cardOptionsSorted.indexOf(it.cards[2]) }
        .thenBy { cardOptionsSorted.indexOf(it.cards[3]) }
        .thenBy { cardOptionsSorted.indexOf(it.cards[4]) }

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
    val answer2 = Day07().part2(readInput("Day07"))
    println(answer2)
}
