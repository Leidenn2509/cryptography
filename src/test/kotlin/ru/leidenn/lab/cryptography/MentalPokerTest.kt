package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.awt.CardLayout
import java.math.BigInteger
import kotlin.random.Random

class MentalPokerTest {
/*    @ParameterizedTest(name = "{0}")
    @CsvSource(
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0",
        "0"
    )
    fun test(a: Int) {
        val p = MentalPoker()
        Assertions.assertEquals((p.c * p.d).mod(p.p - BigInteger.ONE), BigInteger.ONE)

    }*/

/*    @Test
    fun test() {
        val p = Random.nextPrimeBigInteger(BigInteger.valueOf(300))
        val d = (p - BigInteger.ONE).let {
            var buf: BigInteger
            do {
                buf = Random.nextBigInteger(it)
            } while (!(buf < it) || !(fastGCD(buf, it).first == BigInteger.ONE))
            buf
        }
        val c = fastInv(d, p - BigInteger.ONE)
        Assertions.assertEquals((c*d).mod(p - BigInteger.ONE), BigInteger.ONE)
        val n = Random.nextBigInteger(p - BigInteger.ONE)
        assertEquals(n.fastPow(c, p).fastPow(d, p), n)
    }*/

/*    @Test
    fun anotherTest() {
        val a = BigInteger.valueOf(255)
        val b = BigInteger.valueOf(256)
        val c = BigInteger.valueOf(512)
        val d = BigInteger.valueOf(543)
        println(a.toByteArray().joinToString { it.toString() })
        println(b.toByteArray().joinToString { it.toString() })
        println(c.toByteArray().joinToString { it.toString() })
        println(d.toByteArray().joinToString { it.toString() })

    }*/

    @ParameterizedTest(name = "{0}")
    @CsvSource("0", "0", "0", "0", "0", "0", "0", "0", "0", "0", "0")
    fun mainTest(dummy: Int) {
        val p = Random.nextPrimeBigInteger(BigInteger.valueOf(99999))
        val a = MentalPoker(p)
        val b = MentalPoker(p)
        val array = a.firstStep()
        val (bigInteger, arrayList) = b.secondStep(array)
        val thirdStep = a.thirdStep(bigInteger, arrayList)
        b.fourStep(thirdStep)
        println("A=${a.my} | B=${b.my}")
        assertNotEquals(a.my, b.my)
    }
}