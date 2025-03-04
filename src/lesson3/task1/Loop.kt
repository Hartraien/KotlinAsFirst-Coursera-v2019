@file:Suppress("UNUSED_PARAMETER")

package lesson3.task1

import kotlin.math.PI
import kotlin.math.abs
import kotlin.math.sqrt

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result *= i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    if (n == 2) return true
    if (n % 2 == 0) return false
    for (m in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n / 2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
    when {
        n == m -> 1
        n < 10 -> 0
        else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
    }

/**
 * Простая
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun digitNumber(n: Int): Int {
    if (n == 0)
        return 1
    var result = 0
    var number = n
    while (number != 0) {
        result += 1
        number /= 10
    }
    return result
}

/**
 * Простая
 *
 * Найти число Фибоначчи из ряда 1, 1, 2, 3, 5, 8, 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n <= 2)
        return 1
    var prev = 1
    var prevprev = 1
    var result = 0
    for (i in 3..n) {
        result = prev + prevprev
        prevprev = prev
        prev = result
    }
    return result
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
/**
 * Вспомогательный метод
 * вычисляет НОД бинарным методом
 *
 */
fun gcd(m: Int, n: Int): Int {
    var a = m
    var b = n
    if (a == 0)
        return b
    if (b == 0)
        return a
    var k = 0
    while (((a or b) and 1) == 0) {
        a = a shr 1
        b = b shr 1
        k += 1
    }
    while ((a and 1) == 0)
        a = a shr 1
    do {
        while ((b and 1) == 0)
            b = b shr 1
        if (a > b)
            a = b.also { b = a }
        b -= a
    } while (b != 0)
    return a shl k
}

fun lcm(m: Int, n: Int): Int = (m / gcd(m, n)) * n

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    if (n % 2 == 0)
        return 2
    for (i in 3..sqrt(n.toDouble()).toInt() step 2) {
        if (n % i == 0)
            return i
    }
    return n
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    val minDiv = minDivisor(n)
    return if (minDiv == 1)
        1
    else
        n / minDiv
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean = (gcd(m, n) == 1)

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    val minVal = sqrt(m.toDouble()).toInt()
    return minVal * minVal in m..n || (minVal + 1) * (minVal + 1) in m..n
}

/**
 * Средняя
 *
 * Гипотеза Коллатца. Рекуррентная последовательность чисел задана следующим образом:
 *
 *   ЕСЛИ (X четное)
 *     Xслед = X /2
 *   ИНАЧЕ
 *     Xслед = 3 * X + 1
 *
 * например
 *   15 46 23 70 35 106 53 160 80 40 20 10 5 16 8 4 2 1 4 2 1 4 2 1 ...
 * Данная последовательность рано или поздно встречает X == 1.
 * Написать функцию, которая находит, сколько шагов требуется для
 * этого для какого-либо начального X > 0.
 */
fun collatzSteps(x: Int): Int {
    var result = x
    var step = 0
    while (result != 1) {
        result = if (result % 2 == 0) result / 2 else 3 * result + 1
        step += 1
    }
    return step
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю.
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.sin и другие стандартные реализации функции синуса в этой задаче запрещается.
 */
fun sin(x: Double, eps: Double): Double {
    var arg = x
    while (arg > 2 * PI)
        arg -= 2 * PI
    var result = arg
    var addit = arg
    var signVar = 1
    var deg = 1
    while (abs(addit) >= eps) {
        signVar = -signVar
        addit = addit * arg * arg / ((deg + 1) * (deg + 2))
        deg += 2
        result += signVar * addit
    }
    return result
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 * Подумайте, как добиться более быстрой сходимости ряда при больших значениях x.
 * Использовать kotlin.math.cos и другие стандартные реализации функции косинуса в этой задаче запрещается.
 */
fun cos(x: Double, eps: Double): Double {
    var arg = x
    while (arg > 2 * PI)
        arg -= 2 * PI
    var result = 1.0
    var addit = 1.0
    var signVar = 1
    var deg = 0
    while (abs(addit) >= eps) {
        signVar = -signVar
        addit = addit * arg * arg / ((deg + 1) * (deg + 2))
        deg += 2
        result += signVar * addit
    }
    return result
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun revert(n: Int): Int {
    var num = n
    var result = 0
    while (num != 0) {
        result = result * 10 + num % 10
        num /= 10
    }
    return result
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun isPalindrome(n: Int): Boolean = (revert(n) == n)

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun hasDifferentDigits(n: Int): Boolean {
    var num = n
    val elem = num % 10
    while (num != 0) {
        if (num % 10 != elem)
            return true
        num /= 10
    }
    return false
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun lengthOfNumber(n: Int): Int {
    var length = 0
    var div = 1
    while (n / div != 0) {
        div *= 10
        length += 1
    }
    return length
}

fun squareSequenceDigit(n: Int): Int {
    var k = n - 1
    var i = 1
    var len = 1
    var square = i * i
    while (k >= len) {
        k -= len
        i += 1
        square = i * i
        len = lengthOfNumber(square)
    }
    var res = 0
    for (j in 1..(len - k)) {
        res = square % 10
        square /= 10
    }
    return res

}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 *
 * Использовать операции со строками в этой задаче запрещается.
 */
fun fibSequenceDigit(n: Int): Int {
    var k = n - 1
    var i = 1
    var len = 1
    var square = fib(i)
    while (k >= len) {
        k -= len
        i += 1
        square = fib(i)
        len = lengthOfNumber(square)
    }
    var res = 0
    for (j in 1..(len - k)) {
        res = square % 10
        square /= 10
    }
    return res

}
