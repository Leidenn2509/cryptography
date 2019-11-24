package ru.leidenn.lab.cryptography

import java.math.BigInteger
import kotlin.random.Random

class ElGamal(val p: BigInteger, val g: BigInteger) {
    var rec = mutableMapOf<ElGamal, BigInteger>()
    private val c = p.let {
        var res: BigInteger
        val pp = it - BigInteger.ONE
        do {
            res = Random.nextBigInteger(it)
        } while (!(BigInteger.ONE < res) || !(res < pp))
        res
    }
    val d = g.fastPow(c, p)

    fun sendTo(message: BigInteger, to: ElGamal) {
        var k: BigInteger
        val pp = p - BigInteger.valueOf(2)
        do {
            k = Random.nextBigInteger(p)
        } while (!(BigInteger.ONE <= k) || !(k <= pp))
        val r = g.fastPow(k, p)
        val e = (message * (to.d.fastPow(k, p))).mod(p)
        to.recvFrom(r, e, this)
    }

    fun recvFrom(r: BigInteger, e:BigInteger, from: ElGamal) {
        val m = (e * (r.fastPow(p - BigInteger.ONE - c, p))).mod(p)
        rec[from] = m
    }

}