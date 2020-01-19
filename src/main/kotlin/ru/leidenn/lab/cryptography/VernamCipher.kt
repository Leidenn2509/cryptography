package ru.leidenn.lab.cryptography

import java.util.*
import kotlin.random.Random


class VernamCipher(val keyLength: Int) {
    private val key = BitSet(keyLength).apply {
        for (i in 0 until keyLength) {
            if (Random.nextBoolean())
                set(i)
        }
    }

    fun encrypt(message: BitSet): BitSet {
        val n = message.length()
        if (n > keyLength) {
            val newKey = BitSet(n)
            newKey.or(key)
            for (i in keyLength until n) {
                newKey.set(i, key.get(i % keyLength))
            }
            message.xor(newKey)
            return message
        }
        message.xor(key)
        return message
    }

    fun decrypt(message: BitSet): BitSet {
        val n = message.length()
        if (n > keyLength) {
            val newKey = BitSet(n)
            newKey.or(key)
            for (i in keyLength until n) {
                newKey.set(i, key.get(i % keyLength))
            }
            message.xor(newKey)
            return message
        }
        message.xor(key)
        return message
    }

}

fun BitSet.toNiceString(): String {
    val s = StringBuilder()
    for (i in 0 until this.length()) {
        s.append(if (get(i)) "1" else "0")
    }
    return s.toString()
}