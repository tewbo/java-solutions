# HW 5. Свой сканер
## Условие
1.  Реализуйте свой аналог класса [Scanner](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/Scanner.html) на основе [Reader](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/io/Reader.html).
2.  Примените разработанный `Scanner` для решения задания «Реверс».
3.  Примените разработанный `Scanner` для решения задания «Статистика слов».
4.  Нужно использовать блочное чтение. Код, управляющий чтением, должен быть общим.
5.  _Сложный вариант_. Код, выделяющий числа и слова, должен быть общим.

## Модификации
-   _OctAbc_ (38, 39)
    -   На вход подаются десятичные и восьмиричные числа
    -   Восьмиричные числа имеют суффикс `o`
    -   Десятичные числа могут быть записаны буквами: нулю соответствует буква `a`, единице – `b` и так далее
    -   Класс должен иметь имя `ReverseOctAbc`

## Пояснение
Требуется применить быстрый ввод для класса `Reverse` из [[HW 3/README]].