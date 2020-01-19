#### Вторая система электронных денег
В файле [ElMoney](/src/main/kotlin/ru/leidenn/lab/cryptography/ElMoney.kt) мной были написанны три класса: Bank, User, Store  
Класс User посылает запрос классу Bank чтобы получить билет. Далее логика покупки обрабатывается в классе Store  
Там проверяется, что билет валидный и его нет в записях использованных билетов банка


В [тестах](/src/test/kotlin/ru/leidenn/lab/cryptography/ElMoneyTest.kt) проверяется вышеописанная логика

