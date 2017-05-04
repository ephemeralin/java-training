package ru.job4j.array;

/**
* Class RotateArray - класс для поворота квадратного массива по часовой стрелке.
* @author vpiliugin
* @since 0.1
*/
abstract class RotateArray {
  /**
  * Метод rotate для поворота квадратного массива.
  * @param array - исходный массив, который необходимо повернуть
  * @return - возвращает повернутый  массив
  */
  public static final int[][] rotate(int[][] array) {
    int size = array.length;
    int[][] rotatedArray = new int[size][size];
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        rotatedArray[i][j] = array[size - j - 1][i];
      }
    }
    return rotatedArray;
  }
}
