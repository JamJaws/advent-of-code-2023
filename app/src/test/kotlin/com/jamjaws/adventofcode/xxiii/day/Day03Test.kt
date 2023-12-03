package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day03Test {

    private val text = readInput("example/Day03")

    @Test
    fun part1() {
        Day03().part1(text) shouldBe 4361
    }

    @Test
    fun part2() {
        Day03().part2(text) shouldBe 467835
    }

}
