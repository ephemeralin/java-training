package ru.job4j.array;

import static java.util.Arrays.copyOf;

/**
* Class ArrayDuplicate - класс для поворота квадратного массива по часовой стрелке.
* @author vpiliugin
* @since 0.1
*/
abstract class ArrayDuplicate {
  /**
  * Метод remove для удаления дубликатов из массива.
  * @param array - исходный массив, который необходимо обработать
  * @return - возвращает обработанный  массив
  */
  public static final String[] remove(String[] array) {
    int numberOfDublicates = 0;
    int i = 0;
     int endOfArray = array.length;
      while (i < endOfArray) {
        int j = i + 1;
        while (j < endOfArray) {
          if (array[i].equals(array[j])) {
            //send j item to the end of the array
            numberOfDublicates++;
            for (int k = j; k < array.length - 1; k++) {
            	String buffer = array[k];
              array[k] = array[k + 1];
              array[k + 1] = buffer;
            }
            endOfArray--;
            j--;
          }
          j++;
        }
        i++;
      }
      return copyOf(array, array.length - numberOfDublicates);
  }
}
