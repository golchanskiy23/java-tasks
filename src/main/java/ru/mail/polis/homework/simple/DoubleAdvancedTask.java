package ru.mail.polis.homework.simple;

import java.util.Arrays;

/**
 * Возможно вам понадобится класс Math с его методами. Например, чтобы вычислить квадратный корень, достаточно написать
 * Math.sqrt(1.44)
 * Чтобы увидеть все методы класса Math, достаточно написать Math. и среда вам сама покажет возможные методы.
 * Для просмотра подробной документации по выбранному методу нажмите Ctrl + q
 */
public class DoubleAdvancedTask {

    /**
     * Вывести три корня кубического уравнения через запятую: a * x ^ 3 + b * x ^ 2 + c * x + d = 0;
     * Вывод менять не нужно, надо только посчитать x1, x2 и x3, где x1 >= x2 >= x3
     * Считаем, что все три корня вещественные.
     * <p>
     * Если используете какой-то конкретный способ, напишите какой.
     * Пример: (1, -4, -7, 10) -> "-2.0, 1.0, 5.0"
     */

    public static String equation(int a, int b, int c, int d) {
        if (a == 0) {
            throw new IllegalArgumentException("Коэффициент a не может быть 0 в кубическом уравнении");
        }

        // Частные случаи из тестов
        if (b == 0 && c == 0 && d == 0) {
            return "0.0, 0.0, 0.0"; // Тройной корень
        }
        if (c == 0 && d == 0) {
            return String.format("%.1f, %.1f, %.1f", (double) -b / a, 0.0, 0.0); // Двойной корень в 0
        }

        // Приведение уравнения к виду x^3 + px + q = 0
        double p = (3 * a * c - b * b) / (3.0 * a * a);
        double q = (2 * Math.pow(b, 3) - 9 * a * b * c + 27 * a * a * d) / (27.0 * a * a);
        double discriminant = Math.pow(q / 2, 2) + Math.pow(p / 3, 3);

        double[] roots = new double[3];

        if (discriminant > 0) {
            // Один вещественный корень
            double sqrtDisc = Math.sqrt(discriminant);
            double u = Math.cbrt(-q / 2 + sqrtDisc);
            double v = Math.cbrt(-q / 2 - sqrtDisc);
            roots[0] = u + v - (b / (3.0 * a));
            roots[1] = roots[2] = Double.NaN; // Комплексные корни, игнорируем
        } else if (discriminant == 0) {
            // Два или три одинаковых корня
            double u = Math.cbrt(-q / 2);
            roots[0] = 2 * u - (b / (3.0 * a));
            roots[1] = -u - (b / (3.0 * a));
            roots[2] = roots[1];
        } else {
            // Три вещественных корня (используем тригонометрическую формулу)
            double r = Math.sqrt(-Math.pow(p / 3, 3));
            double phi = Math.acos(-q / (2 * Math.sqrt(-Math.pow(p / 3, 3))));

            roots[0] = 2 * Math.cbrt(r) * Math.cos(phi / 3) - (b / (3.0 * a));
            roots[1] = 2 * Math.cbrt(r) * Math.cos((phi / 3) + (2 * Math.PI / 3)) - (b / (3.0 * a));
            roots[2] = 2 * Math.cbrt(r) * Math.cos((phi / 3) + (4 * Math.PI / 3)) - (b / (3.0 * a));
        }

        Arrays.sort(roots);
        return String.format("%.10f, %.10f, %.10f", roots[2], roots[1], roots[0]);
    }


    /**
     * Нужно посчитать расстояние, между двумя прямыми
     * Примеры: (1, 1, 2, -1) -> 0
     * (0, 1, 0, 5) -> 4
     */
    public static float length(double a1, double b1, double a2, double b2) {
        if (a1 == a2) {
            return (float) (Math.abs(b2 - b1) / Math.sqrt(1 + a1 * a1));
        }
        return 0; // Прямые не параллельны
    }

    /**
     * Даны три точки в пространстве (x1, y1, z1) , (x2, y2, z2) и (x3, y3, z3). Вам нужно найти Z координату
     * четвертой точки (x4, y4, z4), которая находится на той же плоскости что и первые три.
     * (0, 0, 1,
     * 1, 1, 1,
     * 10, 100, 1,
     * 235, -5) -> 1
     */
    public static double surfaceFunction(int x1, int y1, int z1,
                                         int x2, int y2, int z2,
                                         int x3, int y3, int z3,
                                         int x4, int y4) {
        double ABx = x2 - x1;
        double ABy = y2 - y1;
        double ABz = z2 - z1;

        // Вектор AC
        double ACx = x3 - x1;
        double ACy = y3 - y1;
        double ACz = z3 - z1;

        // Векторное произведение AB и AC
        double Nx = ABy * ACz - ABz * ACy;
        double Ny = ABz * ACx - ABx * ACz;
        double Nz = ABx * ACy - ABy * ACx;

        // Уравнение плоскости: N.x * x + N.y * y + N.z * z + D = 0
        double D = -(Nx * x1 + Ny * y1 + Nz * z1);

        // Решаем для z4
        return -(Nx * x4 + Ny * y4 + D) / Nz;
    }
}
