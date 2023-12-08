package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day08Test {

    @Test
    fun part1() {
        Day08().part1(readInput("example/Day08")) shouldBe 2
    }
    @Test
    fun `part1 2`() {
        Day08().part1(readInput("example/Day08_2")) shouldBe 6
    }

    @Test
    fun part2() {
        Day08().part2(readInput("example/Day08_part2")) shouldBe 6
    }

}
