package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day07Test {

    private val text = readInput("example/Day07")

    @Test
    fun part1() {
        Day07().part1(text) shouldBe 6440
    }

    @Test
    fun part2() {
        Day07().part2(text) shouldBe 5905
    }

}
