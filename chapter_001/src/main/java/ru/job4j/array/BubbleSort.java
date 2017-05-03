package ru.job4j.array;

/**
* Class BubbleSort - класс для сортировки массива методом "пузырьковой сортировки".
* @author vpiliugin
* @since 0.1
*/
abstract class BubbleSort {
  /**
  * Метод sort для сортировки.
  * @param array - исходный массив, который необходимо сортировать
  * @return - возвращает отсортированный массив массив
  */
  public static final int[] sort(int[] array) {
    boolean compare = true;
    int buffer;
    while (compare) {
      compare = false;
      for (int i = 0; i < array.length - 1; i++) {
        if (array[i] > array[i + 1]) {
          buffer = array[i];
          array[i] = array[i + 1];
          array[i + 1] = buffer;
          compare = true;
        }
      }
    }
    return array;
  }
}
