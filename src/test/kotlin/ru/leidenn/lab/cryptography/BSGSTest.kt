package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.math.BigInteger

class BSGSTest {
    @ParameterizedTest(name = "{0}^x mod {2} = {1}")
    @CsvSource(
        "2, 30203, 24322, 10000",
        "2, 30323, 21740, 20000",
        "2, 30539, 28620, 1000",
        "2, 30803, 16190, 12345",
        "5, 31607, 30994, 25000"
    )
    fun test(a: BigInteger, p: BigInteger, y: BigInteger, res: BigInteger) {
        Assertions.assertEquals(res, dlog(a, y, p))

    }
}