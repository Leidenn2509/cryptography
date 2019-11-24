package ru.leidenn.lab.cryptography

import java.math.BigInteger
import kotlin.math.log2
import kotlin.random.Random


val javaRandom = java.util.Random()

//const val MAX: BigInteger = BigInteger::max

fun Random.nextBigInteger(n: BigInteger): BigInteger {
    var length = n.bitLength()
    var attempt = 0
    var res: BigInteger
    do {
        if (attempt > 10) {
            length -= 1
            attempt = 0
        }
        res = BigInteger(length, javaRandom)
        attempt += 1
    } while (res > n)
    return res
}

fun Random.nextPrimeBigInteger(n: BigInteger): BigInteger {
    var res: BigInteger
    do {
        res = Random.nextBigInteger(n)
    } while (!res.isPrime())
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

fun BigInteger.isPrime(): Boolean {
    //check via BigInteger.isProbablePrime(certainty)
    if (!isProbablePrime(5))
        return false

    //check if even
    val two = BigInteger.valueOf(2)
    if (two != this && BigInteger.ZERO == this.mod(two))
        return false

    //find divisor if any from 3 to 'number'
    var i = BigInteger.valueOf(3)
    val top = this.sqrt()
    while (i.multiply(i).compareTo(top) < 1) { //start from 3, 5, etc. the odd number, and look for a divisor if any
        if (BigInteger.ZERO == this.mod(i))
        //check if 'i' is divisor of 'number'
            return false
        i = i.add(two)
    }
    return true
}

fun pWithGenerator(n: BigInteger): Pair<BigInteger, BigInteger> {
    val q: BigInteger = Random.nextPrimeBigInteger(n)
    val p = BigInteger.valueOf(2) * q + BigInteger.ONE
    var g: BigInteger
    val pp = p - BigInteger.ONE
    do {
        g = Random.nextBigInteger(p)
    } while (!(BigInteger.ONE < g) || !(g < pp) || !(g.fastPow(q, p) != BigInteger.ONE))
    return p to g
}