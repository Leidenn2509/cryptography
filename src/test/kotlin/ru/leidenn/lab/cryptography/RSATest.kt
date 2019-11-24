package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.math.BigInteger
import kotlin.random.Random

class RSATest {
    @Test
    fun test() {
        val users = List(10) { RSA() }
        val send = mutableListOf<Triple<RSA, BigInteger, RSA>>()
        users.forEach { a ->
            users.forEach { b ->
                if (a != b) {
                    var message: BigInteger
                    do {
                        message = Random.nextBigInteger(BigInteger.valueOf(999))
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