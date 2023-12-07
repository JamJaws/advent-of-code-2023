package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day06Test {

    private val text = readInput("example/Day06")

    @Test
    fun part1() {
        Day06().part1(text) shouldBe 288
    }

    @Test
    fun part2() {
        Day06().part2(text) shouldBe 71503
    }

}
