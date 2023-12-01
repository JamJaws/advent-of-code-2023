package com.jamjaws.adventofcode.xxiii

fun readInput(name: String) =
    (object {}.javaClass.getResourceAsStream("/input/$name.txt") ?: throw RuntimeException("File $name.txt not found"))
        .bufferedReader().readLines()
