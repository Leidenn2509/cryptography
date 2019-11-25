package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RSASignTest {
    @Test
    fun test() {
        val a = RSASignOwner()
        val b = RSASignOwner()
        val asign = a.signMessage("hi")
        Assertions.assertTrue(asign.check(a))
        Assertions.assertFalse(asign.check(b))
    }
}