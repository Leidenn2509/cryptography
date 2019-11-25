package ru.leidenn.lab.cryptography

import java.math.BigInteger
import kotlin.random.Random

class ElGamalSignOwner(val p: BigInteger, val g: BigInteger) {
    private val x = run {
        var x: BigInteger
        do {
            x = Random.nextBigInteger(p)
        } while (!(BigInteger.ONE < x) || !(x < (p - BigInteger.ONE)))
        x
    }
    val y = g.fastPow(x, p)

    fun signMessage(message: String): ElGamalSign {
        val h = (message.hashCode().toBigInteger().mod(p) + BigInteger.ONE).mod(p)
        var k: BigInteger
        val pp = p - BigInteger.ONE
        do {
            k = Random.nextBigInteger(p)
        } while (!(BigInteger.ONE < k) || !(k < pp) || !(fastGCD(
                k,
                pp
            ).first == BigInteger.ONE)
        )
        val r = g.fastPow(k, p)
        val u = (h - (x * r).mod(pp)).mod(pp)
        val kk = fastInv(k, pp)
        val s = (kk * u).mod(pp)
        return ElGamalSign(message, r, s)
    }
}

data class ElGamalSign(
    val message: String,
    val r: BigInteger,
    val s: BigInteger
) {
    fun check(owner: ElGamalSignOwner) : Boolean {
        val h = (message.hashCode().toBigInteger().mod(owner.p) + BigInteger.ONE).mod(owner.p)
        return (owner.y.fastPow(r, owner.p) * r.fastPow(s, owner.p)).mod(owner.p) == owner.g.fastPow(h, owner.p)
    }
}