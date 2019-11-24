package ru.leidenn.lab.cryptography

import java.math.BigInteger
import kotlin.random.Random

class RSA {
    var rec = mutableMapOf<RSA, BigInteger>()
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

    fun sendTo(message: BigInteger, to: RSA) {
        val e = message.fastPow(to.d, to.n)
        to.recvFrom(e, this)
    }

    fun recvFrom(m: BigInteger, from: RSA) {
        val message = m.fastPow(c, n)
        rec[from] = message
    }
}