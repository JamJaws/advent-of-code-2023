package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day11Test {

    @Test
    fun `part 1 example`() {
        Day11().part1(readInput("example/Day11")) shouldBe 374
    }

    @Test
    fun `part 1 personal`() {
        Day11().part1(readInput("Day11")) shouldBe 9769724
    }

    @Test
    fun `part 2 example`() {
        Day11().part2(readInput("example/Day11")) shouldBe 82000210
    }

    @Test
    fun `part 2 personal`() {
        Day11().part2(readInput("Day11")) shouldBe 603020563700
    }

}
