package ru.mail.polis.homework.functions;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntUnaryOperator;
import java.util.function.UnaryOperator;

public class SimpleFunction {

    /**
     * Функция от трех аргументов. Не забудьте добавить дженерики.
     * Функция должна походить на {@link java.util.function.BiFunction}
     * 1 балл
     */
    @FunctionalInterface
    interface TerFunction<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    /**
     * Реализуйте каррирование для функции от трех аргументов.
     * Вам нужно правильно определить тип возвращаемого значения и реализовать метод.
     * Не забывайте использовать дженерики.
     * 2 балла
     */
    static <T, U, V, R> Function<T, Function<U, Function<V, R>>> curring(TerFunction<T, U, V, R> terFunction) {
        return t -> u -> v -> terFunction.apply(t, u, v);
    }

    /**
     * Надо вернуть функцию, которая из строки делает квадратное уравнение от квадратного уравнения от g(str):
     * f(str) -> square(square(g(str)))
     * square(x) -> a * x * x + b * x + c
     * doubleStringEquation(1, 1, 1, 1, 0, 2, String::length).apply("cat") = (9 + 2) * (9 + 2) + (9 + 2) + 1 = 133
     * 2 балла
     */
    static Function<String, Double> doubleStringEquation(double a1, double b1, double c1,
                                                         double a2, double b2, double c2,
                                                         Function<String, Double> g) {
        Function<Double, Double> square1 = x -> a1 * x * x + b1 * x + c1;
        Function<Double, Double> square2 = x -> a2 * x * x + b2 * x + c2;
        return str -> square2.apply(square1.apply(g.apply(str)));
    }

    /**
     * Превращает список унарных операторов в один унарный оператор для списка чисел. Получившийся оператор
     * берет каждый элемент из списка чисел и последовательно применяет все входящие операторы.
     * Пример: multifunctionalMapper.apply([x -> x, x -> x + 1, x -> x * x]).apply([1, 2]) = [1, 2, 4, 2, 3, 9]
     * 4 балла (доп задание)
     */
    public static final Function<List<IntUnaryOperator>, UnaryOperator<List<Integer>>> multifunctionalMapper =
            functions -> numbers -> {
                List<Integer> result = new java.util.ArrayList<>();
                for (Integer number : numbers) {
                    for (IntUnaryOperator function : functions) {
                        result.add(function.applyAsInt(number));
                    }
                }
                return result;
            };

    /**
     * Написать функцию, которая принимает начальное значение и преобразователь двух чисел в одно, возвращает функцию,
     * которая на заданном интервале (входящие аргументы результирующей функции) считает преобразование всех целых чисел
     * на заданном интервале.
     *
     * Пример хотим просуммировать числа от 2 до 10:
     * reduceIntOperator.apply(начальное значение, (x,y) -> ...).apply(2, 10) = 54
     * 2 балла
     */
    public static final BiFunction<Integer, IntBinaryOperator, IntBinaryOperator> reduceIntOperator =
            (initialValue, operator) -> (start, end) -> {
                int result = initialValue;
                for (int i = start; i <= end; i++) {
                    result = operator.applyAsInt(result, i);
                }
                return result;
            };
}