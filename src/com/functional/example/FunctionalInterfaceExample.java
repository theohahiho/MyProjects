package com.functional.example;

@java.lang.FunctionalInterface
public interface FunctionalInterfaceExample {
  void show();

  default void doNothing() {
    System.out.println("I do nothing");
  }

  default void doNothing2() {
    System.out.println("I do nothing");
  }

}
