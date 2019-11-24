package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger


class UtilsTest {
    @ParameterizedTest(name = "fastGCD({0}, {1}) = {0}.gcd({1})")
    @CsvSource(
        "8, 16",
        "15, 7",
        "1024, 31"
    )
    fun gcd(a: BigInteger, b: BigInteger) {
        Assertions.assertEquals(fastGCD(a, b).first, a.gcd(b))
    }

    @ParameterizedTest(name = "inv({0}, {1}) = {0}.modInverse({1})")
    @CsvSource(
        "8, 12",
        "15, 23",
        "2, 32",
        "1023123, 13223443212",
        "7, 13",
        "11, 1231413",
        "17, 5232"
    )
    fun inv(a: BigInteger, p: BigInteger) {
        val res = fastInv(a, p)
        if (res != BigInteger.ZERO)
            Assertions.assertEquals(res, a.modInverse(p))
    }

    @ParameterizedTest(name = "{0}.fastPow({1}, {2}) = {0}.pow({1}).mod({2})")
    @CsvSource(
        "2, 3, 10",
        "2, 3, 5",
        "2, 10, 10",
        "2, 10, 2000",
        "2, 1000, 10",
        "2, 100, 999999999999999"
    )
    fun pow(base: BigInteger, power: Int, p: BigInteger) {
        Assertions.assertEquals(base.pow(power).mod(p), base.fastPow(BigInteger.valueOf(power.toLong()), p))
    }
}