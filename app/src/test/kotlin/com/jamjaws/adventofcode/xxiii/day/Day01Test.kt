package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day01Test {

    @Test
    fun part1() {
        Day01().part1(readInput("example/Day01_part1")) shouldBe 142
    }

    @Test
    fun part2() {
        Day01().part2(readInput("example/Day01_part2")) shouldBe 281
    }
}
