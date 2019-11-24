#### Система Диффи-Хеллмана
Для этой лабороторной были реализованны вспомогательные функции [fastPow, fastGCD, fastInv](/src/main/kotlin/ru/leidenn/lab/cryptography/Utils.kt)  
Мной был написанн абстрактный класс [DiffieHellmanUser](/src/main/kotlin/ru/leidenn/lab/cryptography/DiffieHellmanUser.kt) который генерирует случайное число Х и на его основе подсчитывает Y  
Метод generateKey генерирует общий секретный ключ

В [тестах](/src/test/kotlin/ru/leidenn/lab/cryptography/DiffieHellmanUserTest.kt) я создаю 10 пользователей для каждой пары генерирую общий секретный ключ  
Тест гарантирует, что ключи совпадают
