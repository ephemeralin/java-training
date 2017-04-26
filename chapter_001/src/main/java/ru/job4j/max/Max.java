package ru.job4j.max;

/**
* Class Max - класс для определения максимального числа из двух.
* @author vpiliugin
* @since 23.04.2017
*/
abstract class Max {
  /**
  * Метод max для определения максимального числа из двух.
  * @param first - первое число
  * @param second - второе число
  * @return - возвращает максимальное число
  */
  public static int max(int first, int second) {
    return (first > second) ? first : second;
  }

  /**
  * Метод max для определения максимального числа из трех.
  * @param first - первое число
  * @param second - второе число
  * @param third - третье число
  * @return - возвращает максимальное число
  */
  public static int max(int first, int second, int third) {
    int maxvalue = max(first, second);
    maxvalue = max(maxvalue, third);
    return maxvalue;
  }


}
