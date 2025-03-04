@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

// Урок 2: ветвления (здесь), логический тип (см. 2.2).
// Максимальное количество баллов = 6
// Рекомендуемое количество баллов = 5
// Вместе с предыдущими уроками = 9/12

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая (2 балла)
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String =
    if (age in 5..20 || age == 111) "$age лет"
    else if (age % 10 in 2..4) "$age года"
    else if (age % 10 == 1) "$age год"
    else "$age лет"

/**
 * Простая (2 балла)
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(
    t1: Double, v1: Double,
    t2: Double, v2: Double,
    t3: Double, v3: Double
): Double {
    var s = (v1 * t1 + v2 * t2 + v3 * t3) / 2
    var a: Double = s / v1
    var a2: Double
    if (a <= t1) {
        println("$a")
        return a
    } else a = t1
    a2 = ((s - (t1 * v1)) / v2)
    if (a2 <= t2) {
        a += a2
        println("$a")
        return a
    } else a += t2
    a += ((s - (v1 * t1) - (v2 * t2)) / v3)
    println("$a")
    return a
}

/**
 * Простая (2 балла)
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */

fun whichRookThreatens(
    kingX: Int, kingY: Int,
    rookX1: Int, rookY1: Int,
    rookX2: Int, rookY2: Int
): Int {
    if ((rookX1 == kingX || rookY1 == kingY) && (rookX2 == kingX || rookY2 == kingY))
        return 3
    else if (rookX1 == kingX || rookY1 == kingY)
        return 1
    else if (rookX2 == kingX || rookY2 == kingY)
        return 2
    else return 0
}

/**
 * Простая (2 балла)
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(
    kingX: Int, kingY: Int,
    rookX: Int, rookY: Int,
    bishopX: Int, bishopY: Int
): Int {
    if ((rookX == kingX || rookY == kingY) && abs(kingX - bishopX) == abs(kingY - bishopY))
        return 3
    else if (abs(kingX - bishopX) == abs(kingY - bishopY))
        return 2
    else if (rookX == kingX || rookY == kingY)
        return 1
    else return 0
}

/**
 * Простая (2 балла)
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    var max: Double
    var min1: Double
    var min2: Double
    var t1: Double
    var t2: Double
    if ((a + b > c) && (a + c > b) && (b + c > a)) {
        if (a < b) {
            min2 = a
            min1 = b
        } else {
            min2 = b
            min1 = a
        }
        if (min1 < c) max = c
        else {
            max = min1
            min1 = c
        }
        t1 = max.pow(2)
        t2 = min1.pow(2) + min2.pow(2)
        if (t1 < t2) return 0
        else if (t1 == t2) return 1
        else return 2
    } else return -1
}

/**
 * Средняя (3 балла)
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    if ((c <= b) && (d > b) && (d > a)) {
        if (c < a) return b - a
        else return b - c
    }else if ((c > a) && (d < b) && (b > c)) return d - c
    else if ((c <= b) && (d < b) && (d > a)) return d - a
    else return -1
}

fun main() {
    println(timeForHalfWay(4.0, 3.0, 1.0, 4.0, 1.0, 6.0))
}
