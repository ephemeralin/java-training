package ru.job4j.calculator;

/**
* Class Calculator - класс для реализации простейшего калькулятора.
* @author vpiliugin
* @since 21.04.2017
*/
public class Calculator {
  /**
  * Поля класса.
  * @param result - сюда записывается результат арифметической операции
  */
  private double result;

  /**
  * Метод для сложения двух чисел.
  * @param first  - первое число
  * @param second - второе число
  */
  public  void add(final double first, final double second) {
    this.result = first + second;
  }

  /**
  * Метод для вычитания одного числа из другого.
  * @param first  - первое число
  * @param second - второе число
  */
  public final void subtract(final double first, final double second) {
    this.result = first - second;
  }

  /**
  * Метод для умножения двух чисел.
  * @param first  - первое число
  * @param second - второе число
  */
  public final void multiply(final double first, final double second) {
    this.result = first * second;
  }

  /**
  * Метод для деления двух чисел.
  * @param first  - первое число
  * @param second - второе число
  */
  public final void divide(final double first, final double second) {
    this.result = first / second;
  }

  /**
  * Метод для получения результата.
  * @return - getter поля result
  */
  public final double getResult() {
        return this.result;
    }

}
