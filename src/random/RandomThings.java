package random;

import java.util.List;

public class RandomThings {

    private RandomThings() {
    }

    /**
     * An extremely crazy implementation of checking whether a number is dividable by 3;
     * it determines it by summing up the digits of the number and by using recursion.
     *
     * e.g.: isDividableBy3(576) -> isDividableBy3(5 + 7 + 6 = 18) -> isDividableBy3(1 + 8 = 9) -> true
     *
     * (A bit simpler solution would be: num % 3 == 0 :DDD)
     */
    public static boolean isDividableBy3(int num) {
        if (List.of(0, 3, 6, 9).contains(Math.abs(num)))
            return true;
        if (num > 9)
            return isDividableBy3(digitSum(num));
        return false;
    }

    private static int digitSum(int num) {
        return digitSum(num, 10);
    }

    private static int digitSum(int num, int radix) {
        if (num == 0)
            return 0;
        return num % radix + digitSum(num / radix);

        /* Iteratively:
         *   int sum = 0;
         *   while(num > 0) {
         *      sum += (num % radix);
         *      num /= radix;
         *   }
         *   return sum;
         */
    }
}
