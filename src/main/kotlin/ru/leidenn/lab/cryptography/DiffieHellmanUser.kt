package ru.leidenn.lab.cryptography

import java.math.BigInteger
import kotlin.random.Random

abstract class DiffieHellmanUser(val p: BigInteger, val g: BigInteger) {
    private val x: BigInteger = generatePrivateKey(p, g)
    val y = g.fastPow(x, p)


    private val connections = mutableMapOf<DiffieHellmanUser, BigInteger>()
    infix fun generateKey(other: DiffieHellmanUser) {
        val z = other.y.fastPow(x, p)
        connections[other] = z
        other.connections[this] = z
    }

    fun sameKey(other: DiffieHellmanUser) : Boolean {
        return connections[other] == other.connections[this]
    }

    companion object {
        fun generatePrivateKey(p: BigInteger, g: BigInteger): BigInteger {
            val pDiv = p / BigInteger.valueOf(2)
            return Random.nextBigInteger(pDiv) + pDiv
        }
    }
}