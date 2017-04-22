package ru.job4j;

/**
* Class Calculate - класс для вывода строки "Hello world!" на экран.
* @author vpiliugin
* @since 19.04.2017
*/
abstract class Calculate {
  /**
  * Конструктор, вывод строки в консоль.
  * @param args - аргументы, переданные в программу в виде ключей запуска
  */
  public static void main(final String[] args) {
    System.out.println("Hello world!");
  }
}
