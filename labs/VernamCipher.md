#### Шифр Вернама
В файле [VernamCipher](/src/main/kotlin/ru/leidenn/lab/cryptography/VernamCipher.kt) был написанн класс генерирующий случайнный ключ  
И два метода осуществляющих шифровку и дешифровку. Дополнительно разбирается случай когда ключ меньше чем длинна сообщения. В таком случае необходимо "нарастить" ключ  



В [тестах](/src/test/kotlin/ru/leidenn/lab/cryptography/VernamCipher.kt) проверяется вышеописанная логика

