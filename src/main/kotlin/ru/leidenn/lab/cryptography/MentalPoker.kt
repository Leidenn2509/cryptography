package ru.leidenn.lab.cryptography

//import ru.leidenn.lab.cryptography.MentalPoker.CARD.Companion.withCode
import java.awt.CardLayout
import java.math.BigInteger
import kotlin.random.Random

class MentalPoker(val p: BigInteger) {
    var my: CARD? = null
    //    val p = Random.nextPrimeBigInteger(BigInteger.valueOf(99999))
    val d = (p - BigInteger.ONE).let {
        var buf: BigInteger
        do {
            buf = Random.nextBigInteger(it)
        } while (!(buf < it) || !(fastGCD(buf, it).first == BigInteger.ONE))
        buf
    }
    val c = fastInv(d, p - BigInteger.ONE)

    fun firstStep(): ArrayList<BigInteger> {
        val pp = p / BigInteger.valueOf(256)
        var r1 = Random.nextBigIntegerLess(pp)
        val r2 = Random.nextBigIntegerLess(pp)
        val r3 = Random.nextBigIntegerLess(pp)

        val R1 = BigInteger(r1.toByteArray().plus(CARD.A.code))
        val R2 = BigInteger(r2.toByteArray().plus(CARD.B.code))
        val R3 = BigInteger(r3.toByteArray().plus(CARD.C.code))
        val list = arrayListOf(R1, R2, R3)
        val shuffled = list.shuffled()
        return shuffled.map { it.fastPow(c, p) } as ArrayList<BigInteger>
    }

    fun secondStep(array: ArrayList<BigInteger>): Pair<BigInteger, ArrayList<BigInteger>> {
        val n = array.size
        val i = Random.nextInt(n)

        val a = array[i]
        array.remove(a)
        return a to array.map { it.fastPow(c, p) } as ArrayList
    }

    fun thirdStep(a: BigInteger, array: ArrayList<BigInteger>): BigInteger {
//        my = a.fastPow(d, p).toString(10).takeLast(2).let { CARD.withCode(it) }
        my = a.fastPow(d, p).toByteArray().last().let { CARD.withCode(it) }

        val i = Random.nextInt(array.size)
        val a = array[i].fastPow(d, p)
        return a
    }

    fun fourStep(a: BigInteger) {
//        my = a.fastPow(d, p).toString(10).takeLast(2).let { CARD.withCode(it) }
        my = a.fastPow(d, p).toByteArray().last().let { CARD.withCode(it) }
    }


}

enum class CARD(val code: Byte) {
    A(0b00000000),
    B(0b00000001),
    C(0b00000010);

    companion object {
        fun withCode(code: Byte): CARD {
            return CARD.values().find { it.code == code } ?: error("Can't find card with code $code")
        }
    }
}