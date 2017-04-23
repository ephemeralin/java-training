package ru.job4j.condition;

/**
* Class Calculator - класс, описывающий точку в системе координат.
* @author vpiliugin
* @since 22.04.2017
*/
public class Point {
  /**
  * Поле класса.
  * @param x - координата x
  */
  private int x;
  /**
  * Поле класса.
  * @param y - координата y
  */
  private int y;

  /**
  * Конструктор точки с заданными координатами.
  * @param x - координата x
  * @param y - координата y
  */
  public  Point(int x, int y) {
      this.x = x;
      this.y = y;
  }

  /**
  * Геттер координаты x.
  * @return - возвращает координату x
  */
  public int getX() {
    return this.x;
  }

  /**
  * Геттер координаты y.
  * @return - возвращает координату y
  */
  public int getY() {
    return this.y;
  }

  /**
  * Метод для определения, находится ли точка на функции y(x) = a * x + b;.
  * @param a  - первый коэффициент функции
  * @param b - второй коэффициент функции
  * @return - возвращает true, если точка находится на функции
  */
  public boolean is(int a, int b) {
    return this.getY() == a * this.getX() + b;
  }

}
