package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day04Test {

    private val text = readInput("example/Day04")

    @Test
    fun part1() {
        Day04().part1(text) shouldBe 13
    }

}
