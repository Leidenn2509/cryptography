package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class ElGamalSignTest {
    @Test
    fun test() {
        val p = 31259.toBigInteger()
        val g = 2.toBigInteger()
        val a = ElGamalSignOwner(p, g)
        val b = ElGamalSignOwner(p, g)
        val asign = a.signMessage("hi")
        Assertions.assertTrue(asign.check(a))
        Assertions.assertFalse(asign.check(b))
    }
}