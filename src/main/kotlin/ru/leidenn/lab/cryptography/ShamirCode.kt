package ru.leidenn.lab.cryptography

import java.math.BigInteger
import javax.print.attribute.standard.JobMessageFromOperator
import kotlin.random.Random

class ShamirCode {
    private val connections = mutableMapOf<ShamirCode, Triple<BigInteger, BigInteger, BigInteger>>()
    var rec = mutableMapOf<ShamirCode, BigInteger>()
    fun sendMsgTo(message: BigInteger, to: ShamirCode) {
        var p: BigInteger
        do {
            p = Random.nextBigInteger(message*BigInteger.valueOf(10))
        } while(p < message || !p.isPrime())
        val (c, d) = generateCD(p)
        connections[to] = Triple(p, c, d)
        to.sendP(p, this)
        val mcacb = to.sendFirst(message.fastPow(c, p), this)
        to.sendSeccond(mcacb.fastPow(d, p), this)
    }

    fun sendP(p: BigInteger, from: ShamirCode) {
        val (c, d) = generateCD(p)
        connections[from] = Triple(p, c, d)
    }

    private fun sendFirst(mca: BigInteger, from: ShamirCode): BigInteger {
        val (p, c, d) = connections.get(from)!!
        val mcacb = mca.fastPow(c, p)
        return mcacb
    }

    private fun sendSeccond(mcacbda: BigInteger, from: ShamirCode) {
        val (p, c, d) = connections.get(from)!!
        rec[from] = mcacbda.fastPow(d, p)
    }

    companion object {
        val BIG_INTEGER: BigInteger = BigInteger.valueOf(999999)

        fun generateCD(p: BigInteger): Pair<BigInteger, BigInteger> {
            var c: BigInteger
            var d: BigInteger
            do {
                do {
                    c = Random.nextBigInteger(p / BigInteger.valueOf(2))
                    if (c.lowestSetBit != 0) c += BigInteger.ONE
                } while (fastGCD(c, p - BigInteger.ONE).first != BigInteger.ONE)
                d = fastInv(c, p - BigInteger.ONE)
            } while ((c * d).mod(p - BigInteger.ONE) != BigInteger.ONE && c != d)
            return c to d
        }

    }
}