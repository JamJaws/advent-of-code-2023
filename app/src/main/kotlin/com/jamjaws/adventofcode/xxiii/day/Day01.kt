package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.App
import com.jamjaws.adventofcode.xxiii.readInput

class Day01 {

    fun run(text: List<String>): Int =
        text.map { line -> line.filter(Char::isDigit) }
            .map { digits -> "${digits.first()}${digits.last()}" }
            .map(String::toInt)
            .sum()

}

fun main() {
    val answer = Day01().run(readInput("Day01"))
    println(answer)
}
