package ru.job4j.loop;

/**
* Class Factorial - класс для вычисления факториала.
* @author vpiliugin
* @since 30.04.2017
*/
abstract class Factorial {
  /**
  * Метод calc для вычисления факториала.
  * @param n - число, для которого необходимо вычислить факториал
  * @return - возвращает значение факториала
  */
  public static int calc(int n) {
    if (n < 0) {
      return -1;
    }
    if (n == 0) {
      return 1;
    }
    int result = 1;
    for (int i = 1; i <= n; i++) {
      result *= i;
    }
    return result;
  }
}
