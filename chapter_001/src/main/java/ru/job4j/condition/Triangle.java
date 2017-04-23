package ru.job4j.condition;

/**
* Class Triangle - класс, описывающий точку в системе координат.
* @author vpiliugin
* @since 22.04.2017
*/
public class Triangle {

  /**
  * Вершина треугольника 1.
  * @param a - вершина треугольника 1
  */
  private Point a;

  /**
  * Вершина треугольника 2.
  * @param b - вершина треугольника 2
  */
  private Point b;

  /**
  * Вершина треугольника 3.
  * @param c - вершина треугольника 3
  */
  private Point c;

  /**
  * Конструктор треугольника по трем точкам.
  * @param a - точка 1
  * @param b - точка 2
  * @param c - точка 3
  */
  public  Triangle(Point a, Point b, Point c) {
    this.a = a;
    this.b = b;
    this.c = c;
  }

  /**
  * Метод для определения, может ли существовать треугольник с заданными вершинами.
  * @return - true, если треугольник может существовать
  */
  public boolean isTrueTriangle() {
    boolean isTrue = true;

    if (this.a.getX() == this.b.getX() & this.a.getY() == this.b.getY()
        || this.a.getX() == this.c.getX() & this.a.getY() == this.c.getY()
        || this.b.getX() == this.c.getX() & this.b.getY() == this.c.getY()) {
            isTrue = false;
    }

    if (this.a.getX() == this.b.getX() & this.a.getX() == this.c.getX()
        || this.a.getY() == this.b.getY() & this.a.getY() == this.c.getY()) {
            isTrue = false;
    }

    return isTrue;
  }

  /**
  * Метод для вычисления площади треугольника.
  * @return - возвращает значение площади треугольника
  */
  public double area() {
    if (this.isTrueTriangle()) {
        return  Math.abs(0.5 * ((this.a.getX() - this.c.getX()) * (this.b.getY() - this.c.getY())
                    - (this.b.getX() - this.c.getX()) * (this.a.getY() - this.c.getY())));
    } else {
      return -1;
    }

  }

}
