package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day05Test {

    private val text = readInput("example/Day05")

    @Test
    fun part1() {
        Day05().part1(text) shouldBe 35
    }

    @Test
    fun part2() {
        Day05().part2(text) shouldBe 46
    }

}
