package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day10Test {

    @Test
    fun `part 1 example 1`() {
        Day10().part1(readInput("example/Day10")) shouldBe 4
    }

    @Test
    fun `part 1 example 2`() {
        Day10().part1(readInput("example/Day10_2")) shouldBe 8
    }

    @Test
    fun `part 1 personal`() {
        Day10().part1(readInput("Day10")) shouldBe 6690
    }

    @Test
    fun `part 2 example 1`() {
        Day10().part2(readInput("example/Day10_part2")) shouldBe 4
    }

    @Test
    fun `part 2 example 2`() {
        Day10().part2(readInput("example/Day10_part2_2")) shouldBe 4
    }

    @Test
    fun `part 2 example 3`() {
        Day10().part2(readInput("example/Day10_part2_3")) shouldBe 8
    }

    @Test
    fun `part 2 personal`() {
        Day10().part2(readInput("Day10")) shouldBe 525
    }

}
