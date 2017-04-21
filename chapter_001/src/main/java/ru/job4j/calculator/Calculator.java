package ru.job4j.calculator;

/**
* Class Calculator - класс для реализации простейшего калькулятора.
* @author vpiliugin
* @since 21.04.2017
*/
public class Calculator {
  private double result;

  /**
  * Метод для сложения двух чисел.
  * @param first  - первое число
  * @param second - второе число
  */
  public void add(double first, double second) {
    this.result = first + second;
  }

  /**
  * Метод для вычитания одного числа из другого.
  * @param first  - первое число
  * @param second - второе число
  */
  public void subtract(double first, double second) {
    this.result = first - second;
  }

  /**
  * Метод для умножения двух чисел
  * @param first  - первое число
  * @param second - второе число
  */
  public void multiply(double first, double second) {
    this.result = first * second;
  }

  /**
  * Метод для деления двух чисел
  * @param first  - первое число
  * @param second - второе число
  */
  public void divide(double first, double second) {
    this.result = first / second;
  }

  /**
  * Метод для получения результата
  */
  public double getResult() {
        return this.result;
    }

}
