package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.util.*
import kotlin.random.Random

class VernamCipherTest {
    @ParameterizedTest(name = "keyLength = {0}, message = {1}")
    @CsvSource(
        "8,  1100101011111111",
        "16, 10101111",
        "16, 0011001100110011",
        "16, 1111000011",
        "6,  111110000000"
    )
    fun test(keyLength: Int, message: String) {
        val bitSet = BitSet()
        for (i in message.indices) {
            if (message[i] == '0') {
                continue
            } else if (message[i] == '1') {
                bitSet.set(i)
            } else {
                error("Wrong message. Must be 0/1")
            }
        }
        val vernam = VernamCipher(keyLength)
        val encrypted = vernam.encrypt(bitSet)
        val decrypted = vernam.decrypt(encrypted)
        assertEquals(bitSet, decrypted)
    }

    fun randomBitSet(length: Int): BitSet {
        val res = BitSet(length)
        for (i in 0 until length) {
            if (Random.nextBoolean()) {
                res.set(i)
            }
        }
        return res
    }

    companion object {

    }
}

fun BitSet.toNiceString(): String {
    val s = StringBuilder()
    for (i in 0 until this.length()) {
        s.append(if (get(i)) "1" else "0")
    }
    return s.toString()
}