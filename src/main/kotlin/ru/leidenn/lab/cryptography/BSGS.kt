package ru.leidenn.lab.cryptography

import java.math.BigInteger

fun dlog(a: BigInteger, y: BigInteger, p: BigInteger): BigInteger {
    val k = p.sqrt() + BigInteger.ONE
    val m = k
    val first = Array(m.toInt()) {
        (y * (a.fastPow(it.toBigInteger(), p))).mod(p)
    }
    val second = Array(k.toInt()) {
        a.fastPow((it + 1).toBigInteger() * m, p)
    }
    var res: Pair<Int, Int> = 0 to 0
    var find = false
    for (j in first.indices) {
        for (i in second.indices) {
            if (first[j] == second[i]) {
                res = j to i + 1
                find = true
                break
            }
        }
        if (find)
            break
    }
    return (res.second.toBigInteger() * m) - res.first.toBigInteger()
}
