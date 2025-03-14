package ru.mail.polis.homework.simple;

import java.util.function.ToDoubleFunction;

public class HomeworkTask {

    /**
     * Нужно численно посчитать интеграл от a до b с шагом delta от функции function
     * Для того, что бы получить значение по Y для точки X, надо просто написать function.applyAsDouble(t)
     * Считаем, что функция определена на всем пространстве от a до b
     */
    public static double calcIntegral(double a, double b, ToDoubleFunction<Double> function, double delta) {
        double sum = 0.0;
        for (double x = a; x < b; x += delta) {
            sum += function.applyAsDouble(x) * delta;  // Функция на каждом шаге умножаем на длину интервала
        }
        return sum;
    }

    /**
     * Вывести номер максимальной цифры. Счет начинается слева направо,
     * выводим номер первой максимальной цифры (если их несколько)
     */
    public static int func(long a){
        if(a == 0) return 1;

        int count = 0;
        long temp = a;
        while (temp > 0) {
            temp /= 10;
            count++;
        }
        return count;
    }

    public static byte maxNumber(long a) {
        // Сначала получаем абсолютное значение числа для работы с цифрами.
        long original = a;
        a = Math.abs(a);

        // Ищем количество цифр в числе.
        int count = func(a);
        if(count == 1 && a == 0) return 1;

        // Инициализируем переменные для поиска максимальной цифры.
        int currMax = -1;
        int ansIdx = -1;

        // Проходим по всем цифрам числа с конца.
        int idx = count; // Индекс на последней цифре числа.

        while (a > 0) {
            int digit = (int) (a % 10); // Получаем последнюю цифру.

            // Если цифра больше текущего максимума, обновляем переменные.
            if (digit >= currMax) {
                currMax = digit;
                ansIdx = idx;
            }

            // Сдвигаем число и индекс.
            a /= 10;
            idx--;
        }

        // Возвращаем индекс первой максимальной цифры.
        return (byte) Math.abs(ansIdx);
    }




    /**
     * Даны две точки в пространстве (x1, y1) и (x2, y2). Вам нужно найти Y координату третьей точки (x3, y3),
     * которая находится на той же прямой что и первые две.
     */
    public static double lineFunction(int x1, int y1, int x2, int y2, int x3) {
        return y1 + (double)(y2 - y1) / (x2 - x1) * (x3 - x1);
    }

    /**
     * Даны 4 точки в пространстве A(x1, y1), B(x2, y2), C(x3, y3), D(x4, y4). Найдите площадь выпуклого
     * четырехуголька ABCD.
     */
    public static double square(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        return 0.5 * Math.abs(
                x1 * y2 + x2 * y3 + x3 * y4 + x4 * y1 - (y1 * x2 + y2 * x3 + y3 * x4 + y4 * x1)
        );
    }

}
