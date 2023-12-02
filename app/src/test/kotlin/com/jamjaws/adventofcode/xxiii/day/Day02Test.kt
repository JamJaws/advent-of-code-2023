package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day02Test {

    private val text = readInput("example/Day02")

    @Test
    fun part1() {
        Day02().part1(text) shouldBe 8
    }

    @Test
    fun part2() {
        Day02().part2(text) shouldBe 2286
    }
}
