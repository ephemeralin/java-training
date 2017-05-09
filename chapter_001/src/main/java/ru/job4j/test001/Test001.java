package ru.job4j.test001;

/**
* Class Test001 - класс для определения вхождения подстроки в строку.
* @author vpiliugin
* @since 0.1
*/
abstract class Test001 {
  /**
  * Метод contains для определения вхождения подстроки в строку.
  * @param str - строка, в которую проверяем вхождение подстроки
  * @param subStr - подстрока, вхождение которой проверяем
  * @return - возвращает true, если подстрока входит в строку
  */
  public static boolean contains(String str, String subStr) {
    boolean result = false;
    for (int i = 0; i < str.length(); i++) {
      int k = i;
      for (int j = 0; j < subStr.length(); j++) {
        if (k >= str.length()) {
          result = false;
        }
        char strChar = str.charAt(k);
        char subStrChar = subStr.charAt(j);
        if (subStrChar == strChar) {
          result = true;
        } else {
          result = false;
          break;
        }
        k++;
      }
      if (result) {
        return result;
      }
    }
    return result;
  }
}
