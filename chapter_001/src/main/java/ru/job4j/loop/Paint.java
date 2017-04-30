package ru.job4j.loop;

/**
* Class Paint - класс для рисования пирамиды в псевдо-графике.
* @author vpiliugin
* @since 30.04.2017
*/
public class Paint {
  /**
  * Метод piramid рисует пирамиду.
  * @param h - высота пирамиды (в строках)
  * @return - возвращает пирамиду в виде строки
  */
  public static String piramid(int h) {
    StringBuilder builder = new StringBuilder();
    final String separator = System.getProperty("line.separator");

    int bottom = h * 2 - 1;
    int triangles = 1;
    int spaces;

    for (int i = 1; i <= h; i++) {
      spaces = (bottom - triangles) / 2;
      for (int j = 1; j <= spaces; j++) {
          builder.append(" ");
      }

      for (int j = 1; j <= triangles; j++) {
          builder.append("^");
      }

      for (int j = 1; j <= spaces; j++) {
          builder.append(" ");
      }

      builder.append(separator);
      triangles += 2;
    }
    return builder.toString();
  }
}
