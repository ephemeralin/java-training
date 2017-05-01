package ru.job4j.array;

/**
* Class Turn - класс для переворачивания одномерного массива.
* @author vpiliugin
* @since 01.05.2017
*/
abstract class Turn {
  /**
  * Метод calc для вычисления факториала.
  * @param array - исходный массив, который необходимо перевернуть
  * @return - возвращает перевернутый массив
  */
  public static final int[] back(int[] array) {
    int buffer;
     for (int i = 0; i < array.length / 2; i++) {
       buffer = array[i];
       array[i] = array[array.length - i - 1];
       array[array.length - i - 1] = buffer;
     }
    return array;
  }
}
