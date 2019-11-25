package ru.leidenn.lab.cryptography

import java.math.BigInteger
import kotlin.random.Random

class RSASignOwner {
    private val p = Random.nextPrimeBigInteger(BigInteger.valueOf(99999))
    private val q = Random.nextPrimeBigInteger(BigInteger.valueOf(99999))
    val n = p * q
    private val f = (p - BigInteger.ONE) * (q - BigInteger.ONE)
    val d = f.let {
        var buf: BigInteger
        do {
            buf = Random.nextBigInteger(f)
        } while (!(buf < f) || !(fastGCD(buf, f).first == BigInteger.ONE))
        buf
    }
    private val c = fastInv(d, f)

    fun signMessage(message: String): RSASign {
        val y = message.hashCode().toBigInteger()
        val s = y.fastPow(c, n)
        return RSASign(message, s)
    }
}

data class RSASign(
    val message: String,
    val sign: BigInteger
) {
    fun check(owner: RSASignOwner) : Boolean {
        val y = message.hashCode()
        val w = sign.fastPow(owner.d, owner.n)
        return y.toBigInteger() == w
    }
}