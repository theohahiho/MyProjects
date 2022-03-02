package com.generics.examples;

public class MainGenericClass {

  public static void main(String args[]) {

    GenericExample<Integer, String> example1 = new GenericExample<>(1, "test Integer");
    GenericExample<Double, String> example2 = new GenericExample<>(2.344443334444433333, "test Double");
    GenericExample<Float, String> example3 = new GenericExample<>(Float.valueOf("3.44453423423423"), "test Float");

    example1.printGenericObject();
    System.out.println(example1.getDescription());
    example2.printGenericObject();
    System.out.println(example2.getDescription());
    example3.printGenericObject();
    System.out.println(example3.getDescription());

    example1.addObjectToList(1);

  }

}
