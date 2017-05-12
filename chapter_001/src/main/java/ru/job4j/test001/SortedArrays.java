package ru.job4j.test001;

/**
* Class SortedArrays - класс для работы с отсортированными массивами.
* @author vpiliugin
* @since 0.1
*/
abstract class SortedArrays {
  /**
  * Метод add для слияния двух отсортированных массивов в один отсортированный.
  * @param first - первый массив
  * @param second - второй массив
  * @return - возвращает результирующий массив
  */
  public static int[] add(int[] first, int[] second) {
    int firstLength = first.length;
    int secondLength = second.length;
    int[] result = new int[firstLength + secondLength];

    int i = 0;
    int j = 0;
    int k = 0;
    while (k < result.length) {
      if (i == first.length) {
        result[k] = second[j];
        j++;
      } else if (j == second.length) {
        result[k] = first[i];
        i++;
      } else {
        if (first[i] < second[j]) {
          result[k] = first[i];
          i++;
        } else if (first[i] == second[j]) {
          result[k] = first[i];
          k++;
          result[k] = second[j];
          i++;
          j++;
        } else {
          result[k] = second[j];
          j++;
        }
      }
      k++;
    }
    return result;
  }
}
