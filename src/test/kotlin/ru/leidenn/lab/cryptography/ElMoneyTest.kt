package ru.leidenn.lab.cryptography

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ElMoneyTest {
    @Test
    fun tes() {
        val bank = Bank()
        val userA = User()
        val userB = User()
        val userC = User()
        val store = Store()
        userA.getTicket(bank)
        userB.getTicket(bank)
        userC.getTicket(bank)
        Assertions.assertTrue(store.buy(userA, bank))
        Assertions.assertTrue(store.buy(userC, bank))
        Assertions.assertTrue(store.buy(userB, bank))
    }
}