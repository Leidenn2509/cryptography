package ru.leidenn.lab.cryptography

import java.math.BigInteger
import kotlin.math.log2
import kotlin.random.Random

val javaRandom = java.util.Random()

//const val MAX: BigInteger = BigInteger::max

fun Random.nextBigInteger(n: BigInteger): BigInteger {
    var res: BigInteger
    do {
        res = BigInteger(n.bitLength(), javaRandom)
    } while (res > n)
    return res
}

fun BigInteger.fastPow(power: BigInteger, p: BigInteger): BigInteger {
    var base = this
    var power = power
    base = base.mod(p)
    if (power.signum() == -1) {
        power = power.inv()
        base = fastInv(base, p)
    }
    var res = BigInteger.ONE
    var t = log2(power.toDouble()).toInt() + 1
    val two = BigInteger.valueOf(2)
    repeat(t) {
        if (power.lowestSetBit == 0) {
            res = (res * base).mod(p)
        }
        base = (base * base).mod(p)
        power = power.div(BigInteger.valueOf(2))
    }
    return res
}


fun fastGCD(a: BigInteger, b: BigInteger): Triple<BigInteger, BigInteger, BigInteger> {
    var u = mutableListOf<BigInteger>(a.max(b), BigInteger.ONE, BigInteger.ZERO)
    var v = mutableListOf<BigInteger>(a.min(b), BigInteger.ZERO, BigInteger.ONE)
    val t = mutableListOf<BigInteger>(BigInteger.ZERO, BigInteger.ZERO, BigInteger.ONE)
    var q: BigInteger
    while (v[0] != BigInteger.ZERO) {
        q = u[0] / v[0]
        t[0] = u[0] % v[0]
        t[1] = u[1] - q * v[1]
        t[2] = u[2] - q * v[2]
        u = v.toMutableList()
        v = t.toMutableList()
    }
    return Triple(u[0], u[1], u[2])
}

fun fastInv(m: BigInteger, p: BigInteger): BigInteger {
    var (a, _, x) = fastGCD(m, p)
    if (a != BigInteger.ONE) return BigInteger.ZERO
    if (x.signum() == -1) {
        x += p
    }
    return x
}