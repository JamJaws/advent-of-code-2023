package com.jamjaws.adventofcode.xxiii.day

import com.jamjaws.adventofcode.xxiii.readInput
import java.math.BigInteger

class Day08 {

    fun part1(text: List<String>): Long {
        val path = text.first()
        val nodes = parseNodes(text)
        return getNumberOfStepsUntil("AAA", path, nodes) { node -> node == "ZZZ" }
    }

    private fun String.getNode(
        lr: Char,
        nodes: Map<String, Node>,
    ): String = nodes.getValue(this).let { if (lr == 'L') it.left else it.right }


    fun part2(text: List<String>): Long {
        val path = text.first()
        val nodes = parseNodes(text)

        val steps = nodes.keys.filter { it.endsWith('A') }
            .map { getNumberOfStepsUntil(it, path, nodes) { node -> node.endsWith('Z') } }

        return steps.map(BigInteger::valueOf)
            .reduce { acc, bigInteger ->
                acc.lcm(bigInteger)
            }.toLong()
    }

    private fun parseNodes(text: List<String>) = text.drop(2)
        .map { it.replace("[()]".toRegex(), "").split(" = ", ", ") }
        .associateBy({ (key) -> key }) { (_, left, right) -> Node(left, right) }

    private fun BigInteger.lcm(number: BigInteger): BigInteger = multiply(number).abs().divide(gcd(number))

    private tailrec fun getNumberOfStepsUntil(
        node: String,
        path: String,
        nodes: Map<String, Node>,
        steps: Long = 0,
        predicate: (String) -> Boolean,
    ): Long = if (predicate(node)) {
        steps
    } else {
        getNumberOfStepsUntil(
            node.getNode(path[(steps % path.length).toInt()], nodes),
            path,
            nodes,
            steps + 1,
            predicate
        )
    }

}

data class Node(val left: String, val right: String)

fun main() {
    val answer1 = Day08().part1(readInput("Day08"))
    println(answer1)
    val answer2 = Day08().part2(readInput("Day08"))
    println(answer2)
}
