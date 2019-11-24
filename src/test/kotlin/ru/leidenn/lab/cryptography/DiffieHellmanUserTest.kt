package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.math.BigInteger

class DiffieHellmanUserTest {
    @Test
    fun test() {
        class User(p: BigInteger, g: BigInteger) : DiffieHellmanUser(p, g) {}
        val p = BigInteger.valueOf(30803)
        val g = BigInteger.valueOf(2)
        val users = List(10) { User(p, g) }
        users.forEach { a ->
            users.forEach { b ->
                if(a != b) {
                    a generateKey b
                }
            }
        }
        users.forEach { a ->
            users.forEach { b ->
                if(a != b) {
                    Assertions.assertTrue(a.sameKey(b))
                }
            }
        }

    }
}