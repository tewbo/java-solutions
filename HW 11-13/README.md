# HW 11. Выражения
## Условие
1.  Разработайте классы `Const`, `Variable`, `Add`, `Subtract`, `Multiply`, `Divide` для вычисления выражений с одной переменной в типе `int` (интерфейс `Expression`).
2.  Классы должны позволять составлять выражения вида
	```
    new Subtract(
        new Multiply(
            new Const(2),
            new Variable("x")
        ),
        new Const(3)
    ).evaluate(5)
    ```
    
    При вычислении такого выражения вместо каждой переменной подставляется значение, переданное в качестве параметра методу `evaluate`. Таким образом, результатом вычисления приведенного примера должно стать число 7.
3.  Метод `toString` должен выдавать запись выражения в полноскобочной форме. Например
    ```
    new Subtract(
        new Multiply(
            new Const(2),
            new Variable("x")
        ),
        new Const(3)
    ).toString()
     ``` 
    
    должен выдавать `((2 * x) - 3)`.
4.  _Сложный вариант._ Метод `toMiniString` (интерфейс `ToMiniString`) должен выдавать выражение с минимальным числом скобок. Например
    ```
    new Subtract(
        new Multiply(
            new Const(2),
            new Variable("x")
        ),
        new Const(3)
    ).toMiniString()
    ```
    
    должен выдавать `2 * x - 3`.
5.  Реализуйте метод `equals`, проверяющий, что два выражения совпадают. Например,
	```
    new Multiply(new Const(2), new Variable("x"))
        .equals(new Multiply(new Const(2), new Variable("x")))
                
    ```
    должно выдавать `true`, а
    ```
    new Multiply(new Const(2), new Variable("x"))
        .equals(new Multiply(new Variable("x"), new Const(2)))
                
    ```
    должно выдавать `false`.
6.  Для тестирования программы должен быть создан класс `Main`, который вычисляет значение выражения `x2−2x+1`, для `x`, заданного в командной строке.
7.  При выполнении задания следует обратить внимание на:
    -   Выделение общего интерфейса создаваемых классов.
    -   Выделение абстрактного базового класса для бинарных операций.

## Модификации
-   _Base_
    -   Реализуйте интерфейс [Expression](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/Expression.java)
    -   [Исходный код тестов](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/ExpressionTest.java)
    -   [Исходный код тестов для групп 32-35](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/ExpressionEasyTest.java)
        -   Первый аргумент: `easy` или `hard`.
        -   Последующие аргументы: модификации.
-   _Triple_ (32-39)
    -   Дополнительно реализуйте поддержку выражений с тремя переменными: `x`, `y` и `z`.
    -   Интерфейс [TripleExpression](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/TripleExpression.java).
-   _Double_ (36-39)
    -   Дополнительно реализуйте вычисления в типе `double`.
    -   Интерфейс [DoubleExpression](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/DoubleExpression.java).

# HW 12. Разбор выражений
## Условие
1.  Доработайте предыдущее домашнее задание, так что бы выражение строилось по записи вида
    ```
    x * (x - 2)*x + 1
    ```
2.  В записи выражения могут встречаться:
    -   бинарные операции: умножение `*`, деление `/`, сложение `+` и вычитание `-`;
    -   унарный минус `-`;
    -   переменные `x`, `y` и `z`;
    -   целочисленные константы в десятичной системе счисления, помещающиеся в 32-битный знаковый целочисленный тип;
    -   круглые скобки для явного обозначения приоритета операций;
    -   произвольное число пробельных символов в любом месте, не влияющем на однозначность понимания формулы (например, между операцией и переменной, но не внутри констант).
3.  Приоритет операций, начиная с наивысшего
    1.  унарный минус;
    2.  умножение и деление;
    3.  сложение и вычитание.
4.  Разбор выражений рекомендуется производить [методом рекурсивного спуска](https://ru.wikibooks.org/wiki/%D0%A0%D0%B5%D0%B0%D0%BB%D0%B8%D0%B7%D0%B0%D1%86%D0%B8%D0%B8_%D0%B0%D0%BB%D0%B3%D0%BE%D1%80%D0%B8%D1%82%D0%BC%D0%BE%D0%B2/%D0%9C%D0%B5%D1%82%D0%BE%D0%B4_%D1%80%D0%B5%D0%BA%D1%83%D1%80%D1%81%D0%B8%D0%B2%D0%BD%D0%BE%D0%B3%D0%BE_%D1%81%D0%BF%D1%83%D1%81%D0%BA%D0%B0).
    -   Алгоритм должен работать за линейное время.
    -   Лексический анализ (токенизация) не требуется.

## Модификации
-   _Base_
    -   Класс `ExpressionParser` должен реализовывать интерфейс [TripleParser](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/parser/TripleParser.java)
    -   Результат разбора должен реализовывать интерфейс [TripleExpression](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/TripleExpression.java)
    -   [Исходный код тестов](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/parser/ParserTest.java)
        -   Первый аргумент: `easy` или `hard`
        -   Последующие аргументы: модификации
-   _GcdLcm_ (38, 39)
    -   Дополнительно реализуйте бинарные операции (минимальный приоритет):
        -   `gcd` – НОД, `2 gcd -3` равно 1;
        -   `lcm` – НОК, `2 lcm -3` равно -6.
-   _Reverse_ (38, 39)
    -   Дополнительно реализуйте унарную операцию `reverse` – число с переставленными цифрами, `reverse -12345` равно `-54321`.

# HW 13. Обработка ошибок
## Условие
1.  Добавьте в программу, вычисляющую выражения, обработку ошибок, в том числе:
    -   ошибки разбора выражений;
    -   ошибки вычисления выражений.
2.  Для выражения `1000000*x*x*x*x*x/(x-1)` вывод программы должен иметь следующий вид:
    ```
    x       f
    0       0
    1       division by zero
    2       32000000
    3       121500000
    4       341333333
    5       overflow
    6       overflow
    7       overflow
    8       overflow
    9       overflow
    10      overflow
    ```            
    Результат `division by zero` (`overflow`) означает, что в процессе вычисления произошло деление на ноль (переполнение).
3.  При выполнении задания следует обратить внимание на дизайн и обработку исключений.
4.  Человеко-читаемые сообщения об ошибках должны выводиться на консоль.
5.  Программа не должна «вылетать» с исключениями (как стандартными, так и добавленными).

## Модификации
-   _Base_
    -   Класс `ExpressionParser` должен реализовывать интерфейс [TripleParser](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/exceptions/TripleParser.java)
    -   Классы `CheckedAdd`, `CheckedSubtract`, `CheckedMultiply`, `CheckedDivide` и `CheckedNegate` должны реализовывать интерфейс [TripleExpression](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/TripleExpression.java)
    -   Нельзя использовать типы `long` и `double`
    -   Нельзя использовать методы классов `Math` и `StrictMath`
    -   [Исходный код тестов](https://www.kgeorgiy.info/git/geo/prog-intro-2022/src/branch/master/java/expression/exceptions/ExceptionsTest.java)
        -   Первый аргумент: `easy` или `hard`
        -   Последующие аргументы: модификации
-   _GcdLcm_ (38, 39)
    -   Дополнительно реализуйте бинарные операции (минимальный приоритет):
        -   `gcd` – НОД, `2 gcd -3` равно 1;
        -   `lcm` – НОК, `2 lcm -3` равно -6.
-   _Reverse_ (38, 39)
    -   Дополнительно реализуйте унарную операцию `reverse` – число с переставленными цифрами, `reverse -12345` равно `-54321`.
-   _PowLog10_ (36-39)
    -   Дополнительно реализуйте унарные операции:
        -   `log10` – логарифм по уснованию 10, `log10 1000` равно 3;
        -   `pow10` – 10 в степени, `pow10 4` равно 10000.

# Пояснение к использованию
- Для корректной работы кода, необходимо, чтобы интерфейсы `Expression`, `DoubleExpression` и `TripleExpression`, находились в пакете `expression`, а интерфейс `TripleParser` в пакете `expression.parser`.