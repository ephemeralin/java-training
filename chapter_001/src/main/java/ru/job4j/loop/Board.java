package ru.job4j.loop;

/**
* Class board - класс реализации шахматной доски.
* @author vpiliugin
* @since 30.04.2017
*/
public class Board {
  /**
  * Метод paint для рисования доски.
  * @param width - ширина доски
  * @param height - ширина доски
  * @return - возвращает доску в виде строки
  */
  public static String paint(int width, int height) {
    StringBuilder builder = new StringBuilder();
    final String separator = System.getProperty("line.separator");
    boolean drawX = true;
    for (int i = 1; i <= height; i++) {
      for (int j = 1; j <= width; j++) {
        if (drawX) {
          builder.append("x");
          drawX = false;
        } else {
          builder.append(" ");
          drawX = true;
        }
      }
      builder.append(separator);
    }
    return builder.toString();
  }
}
