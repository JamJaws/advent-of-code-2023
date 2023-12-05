package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput

class Day05 {

    fun part1(text: List<String>): Long {
        val seeds = text.first().substringAfter("seeds: ").split(' ').map(String::toLong)
        val maps = parseMaps(text)
        return seeds.minOf { getLocation(maps, it) }
    }

    fun part2(text: List<String>): Long {
        val maps = parseMaps(text)
        val seedRanges = getSeedRanges(text)
        return seedRanges.minOf { range -> range.minOf { getLocation(maps, it) } }
    }

    private fun getLocation(maps: List<List<ConvertMap>>, seed: Long): Long {
        val seedToSoil = maps.first()
        val soilToFertilizer = maps[1]
        val fertilizerToWater = maps[2]
        val waterToLight = maps[3]
        val lightToTemperature = maps[4]
        val temperatureToHumidity = maps[5]
        val humidityToLocation = maps[6]

        val soil = seed + (seedToSoil.find { seed in it.range }?.diff ?: 0)
        val fertilizer = soil + (soilToFertilizer.find { soil in it.range }?.diff ?: 0)
        val water = fertilizer + (fertilizerToWater.find { fertilizer in it.range }?.diff ?: 0)
        val light = water + (waterToLight.find { water in it.range }?.diff ?: 0)
        val temperature = light + (lightToTemperature.find { light in it.range }?.diff ?: 0)
        val humidity = temperature + (temperatureToHumidity.find { temperature in it.range }?.diff ?: 0)
        return humidity + (humidityToLocation.find { humidity in it.range }?.diff ?: 0)
    }

    private fun parseMaps(text: List<String>) = text.drop(2).fold(listOf<MutableList<ConvertMap>>()) { acc, line ->
        when {
            line.contains("-to-") -> acc + mutableListOf(mutableListOf())

            line.isNotBlank() -> acc.apply {
                last().add(line.split(' ').map(String::toLong).let { ConvertMap(it.first(), it[1], it[2]) })
            }

            else -> acc
        }
    }

    private fun getSeedRanges(text: List<String>) =
        text.first()
            .substringAfter("seeds: ")
            .split(' ')
            .map(String::toLong)
            .chunked(2)
            .map { (start, length) -> start until (start + length) }

}

data class ConvertMap(val destinationRangeStart: Long, val sourceRangeStart: Long, val rangeLength: Long) {
    val range get() = sourceRangeStart until (sourceRangeStart + rangeLength)

    val diff get() = destinationRangeStart - sourceRangeStart
}

fun main() {
    val answer1 = Day05().part1(readInput("Day05"))
    println(answer1)
    val answer2 = Day05().part2(readInput("Day05"))
    println(answer2)
}
