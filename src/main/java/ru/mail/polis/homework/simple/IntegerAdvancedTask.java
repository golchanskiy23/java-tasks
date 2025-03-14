package ru.mail.polis.homework.simple;


/**
 * Возможно вам понадобится класс Math с его методами. Например, чтобы вычислить квадратный корень, достаточно написать
 * Math.sqrt(1.44)
 * Чтобы увидеть все методы класса Math, достаточно написать Math. и среда вам сама покажет возможные методы.
 * Для просмотра подробной документации по выбранному методу нажмите Ctrl + q
 */
public class IntegerAdvancedTask {

    private static final double EPS = 1e-10;

    /**
     * Сумма первых n-членов геометрической прогрессии с первым элементом a и множителем r
     * a + aq + aq^2 + ... + aq^(n-1)
     * <p>
     * Пример: (1, 2, 3) -> 7
     */
    public static long progression(int a, double q, int n) {
        if(q == 1) {
            return (long)a*n;
        }
        return a*(1-(long)Math.pow(q,n))/(long)(1-q);
    }

    /**
     * Гусеница ползает по столу квадратами по часовой стрелке. За день она двигается следующим образом:
     * сначала наверх на up, потом направо на right. Ночью она двигается вниз на down и налево на left.
     * Сколько суток понадобится гусенице, чтобы доползти до поля с травой?
     * Считаем, что на каждой клетке с координатами >= grassX или >= grassY находится трава
     * Если она этого никогда не сможет сделать, Верните число Integer.MAX_VALUE;
     * Пример: (10, 3, 5, 5, 20, 11) -> 2
     */
    public static int func(int up, int down, int target){
        int ansX = 0;
        if(up-target < 0 && (up-down <= 0)){
            return Integer.MAX_VALUE;
        }
        int curr = 0;
        while(true){
            curr += up;
            ++ansX;
            if(curr >= target){break;}
            curr -= down;
        }
        return ansX;
    }

    public static int snake(int up, int right, int down, int left, int grassX, int grassY) {
        return Math.min(func(up, down, grassY), func(right, left, grassX));
    }


    /**
     * Дано число n в 10-ном формате и номер разряда order.
     * Выведите цифру стоящую на нужном разряде для числа n в 16-ом формате
     * Нельзя пользоваться String-ами
     * Пример: (454355, 2) -> D
     */
    public static char kDecimal(int n, int order) {
        int curr = 1;
        char t = ' ';
        char[] arr = "0123456789ABCDEF".toCharArray();
        while(curr <= order){
            t = arr[n%16];
            n /= 16;
            curr++;
        }
        return t;
    }


    /**
     * Дано число в 10-ном формате.
     * Нужно вывести номер минимальной цифры для числа в 16-ном формате. Счет начинается справа налево,
     * выводим номер первой минимальной цифры (если их несколько)
     * Нельзя пользоваться String-ами
     * (6726455) -> 2
     */
    public static byte minNumber(long a) {
        byte minPosition = -1, position = 1, minDigit = 16;

        while (a > 0) {
            byte currentDigit = (byte) (a & 0xF);
            if (currentDigit < minDigit) {
                minDigit = currentDigit;
                minPosition = position;
            }
            a >>= 4;
            position++;
        }
        return minPosition;
    }

}
