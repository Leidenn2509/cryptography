#### RSA подпись
Мной был написан класс [ElGamalSignOwner](/src/main/kotlin/ru/leidenn/lab/cryptography/ElGamalSign.kt) который представляет нам пользователя подписи
С помощью этого класса можно подписать сообщение. Сообщения выдаюдся в виде объекта ElGamalSign который содержит в себе сообщение и подпись и метод для проверки 


В [тестах](/src/test/kotlin/ru/leidenn/lab/cryptography/ElGamalSignTest.kt) я создаю Алису и Боба и подписываю сообщение "hi" подписью Алисы
    

