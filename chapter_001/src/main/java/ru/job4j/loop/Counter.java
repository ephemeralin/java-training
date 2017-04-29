package ru.job4j.loop;

/**
* Class Counter - класс для работы с циклами.
* @author vpiliugin
* @since 29.04.2017
*/
abstract class Counter {
  /**
  * Метод max для определения суммы четных чисел в диапазоне.
  * @param start - первое число диапазона
  * @param finish - последнее число диапазона
  * @return - возвращает сумму четных чисел
  */
  public static int add(int start, int finish) {
    if ((start % 2) != 0) {
      start++;
    }
    int sum = 0;
    for (int i = start; i <= finish; i += 2) {
      sum += i;
    }
    return sum;
  }
}
