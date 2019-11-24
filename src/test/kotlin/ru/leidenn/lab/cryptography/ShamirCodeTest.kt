package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger
import kotlin.random.Random

class ShamirCodeTest {
    @ParameterizedTest(name = "c*d mod {0} - 1 == 1")
    @CsvSource(
        "10",
        "23",
        "45",
        "713894123",
        "123456",
        "99999"
    )
    fun generateCD(p: BigInteger) {
        val (c, d) = ShamirCode.generateCD(p)
        Assertions.assertTrue((c * d).mod(p - BigInteger.ONE) == BigInteger.ONE)
        println("$c $d")
    }

    @Test
    fun test() {
        val users = List(10) { ShamirCode() }
        val send = mutableListOf<Triple<ShamirCode, BigInteger, ShamirCode>>()
        users.forEach { a ->
            users.forEach { b ->
                if (a != b) {
                    var message: BigInteger
                    do {
                        message = Random.nextBigInteger(BigInteger.valueOf(9999))
                    } while (message <= BigInteger.ONE)
                    a.sendMsgTo(message, b)
                    send.add(Triple(a, message, b))
                }
            }
        }
        send.forEach {
            Assertions.assertEquals(it.second, it.third.rec[it.first])
        }

    }
}