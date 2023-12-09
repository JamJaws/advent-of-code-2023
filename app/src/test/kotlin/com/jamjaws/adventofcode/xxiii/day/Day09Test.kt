package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day09Test {

    @Test
    fun part1() {
        Day09().part1(readInput("example/Day09")) shouldBe 114
    }

    @Test
    fun part2() {
        Day09().part2(readInput("example/Day09")) shouldBe 2
    }

}
