package ru.mail.polis.homework.simple;


/**
 * Возможно вам понадобится класс Math с его методами. Например, чтобы вычислить квадратный корень, достаточно написать
 * Math.sqrt(1.44)
 * Чтобы увидеть все методы класса Math, достаточно написать Math. и среда вам сама покажет возможные методы.
 * Для просмотра подробной документации по выбранному методу нажмите Ctrl + q
 */
public class IntegerTask {

    /**
     * Сумма чисел от 1 до n (1 + 2 + 3 + ... + n)
     * Пример: (5) -> 15
     */
    public static int sum(int n) {
        int curr = 0;
        for (int i = 1; i <= n; i++) curr += i;
        return curr;
    }

    /**
     * Гусеница поднимается по стене длиной height на высоту top за день, ночью гусеница сползает на bottom.
     * Сколько дней понадобится гусенице, чтобы доползти до потолка. Если она этого никогда не сможет сделать,
     * Верните число Integer.MAX_VALUE;
     * Пример: (10, 3, 2) -> 8
     */
    public static int snake(int height, int top, int bottom) {
        if(top-height < 0 && (  top-bottom <= 0)){
            return Integer.MAX_VALUE;
        }
        int ans = 0, curr = 0;
        while(true){
            curr += top;
            ++ans;
            if(curr >= height){break;}
            curr -= bottom;
        }
        return ans;
    }

    /**
     * Дано число n и номер разряда order. Выведите цифру стояющую на нужном разряде
     * Пример: (454355, 3) -> 3
     */
    public static int kDecimal(int n, int order) {
        int decimal = 1, curr = n%10;
        while(decimal != order){
            decimal++;
            n /= 10;
            curr = n%10;
        }
        return (curr < 0) ? curr*(-1) : curr;
    }


    /**
     * Выведите факториал от числа n
     * Пример: (5) -> 120
     */
    static long[] fact = new long[21];
    static {
        fact[0] = 1;
        fact[1] = 1;
    }

    public static long factorial(byte n) {
        if (n == 0 || n == 1) return 1;

        if (fact[n] != 0) {
            return fact[n];
        }

        fact[n] = n * factorial((byte) (n - 1));
        return fact[n];
    }
}
