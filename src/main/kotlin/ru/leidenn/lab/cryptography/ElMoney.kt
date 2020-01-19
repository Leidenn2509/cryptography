package ru.leidenn.lab.cryptography

import java.math.BigInteger
import kotlin.random.Random

class Bank {
    private val P = Random.nextPrimeBigInteger(BigInteger.valueOf(99999))
    private val Q = Random.nextPrimeBigInteger(BigInteger.valueOf(99999))
    val N = P * Q
    private val f = (P - BigInteger.ONE) * (Q - BigInteger.ONE)
    val d = f.let {
        var buf: BigInteger
        do {
            buf = Random.nextBigInteger(f)
        } while (!(buf < f) || !(fastGCD(buf, f).first == BigInteger.ONE))
        buf
    }
    private val c = fastInv(d, f)


    private val tickets: HashSet<Pair<BigInteger, BigInteger>> = HashSet()
    fun reqTicket(nn: BigInteger): BigInteger {
        val mm = nn.fastPow(c, N)
//        tickets.add(nn to mm)
        return mm
    }

    fun checkTicket(ticket: Pair<BigInteger, BigInteger>): Boolean {
        assert(ticket.second.fastPow(d, N) == ticket.first)
        assert(!tickets.contains(ticket))
        tickets.add(ticket)
        return true
    }
}

class User {
    var ticket: Pair<BigInteger, BigInteger>? = null
    fun getTicket(bank: Bank) {
        val n = Random.nextBigInteger(bank.N)

        val R = bank.N.let {
            var buf: BigInteger
            do {
                buf = Random.nextBigInteger(it)
            } while (!(buf < it) || !(fastGCD(buf, it).first == BigInteger.ONE))
            buf
        }
        val nn = (n * (R.fastPow(bank.d, bank.N))).mod(bank.N)
        bank.reqTicket(nn)
        val mm = bank.reqTicket(nn)
        val m = (mm * fastInv(R, bank.N)).mod(bank.N)
        ticket = n to m
    }
}

class Store {
    fun buy(user: User, bank: Bank): Boolean {
        val ticket = user.ticket ?: error("user don't have any ticket")
        assert(ticket.second.fastPow(bank.d, bank.N) == ticket.first)
        return bank.checkTicket(ticket)
    }
}