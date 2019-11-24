package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.random.Random

class ElGamalTest {
    @Test
    fun test() {
//        val (p, g) = pWithGenerator(BigInteger.valueOf(50))
        val p = BigInteger.valueOf(30803)
        val g = BigInteger.valueOf(2)
        val users = List(10) { ElGamal(p, g) }
        val send = mutableListOf<Triple<ElGamal, BigInteger, ElGamal>>()
        users.forEach { a ->
            users.forEach { b ->
                if (a != b) {
                    var message: BigInteger
                    do {
                        message = Random.nextBigInteger(BigInteger.valueOf(15))
                    } while (message <= BigInteger.ONE)
                    a.sendTo(message, b)
                    send.add(Triple(a, message, b))
                }
            }
        }
        send.forEach {
            Assertions.assertEquals(it.second, it.third.rec[it.first])
        }

    }
}