package com.functional.example;


import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class MainFunctionalClass {

  public static void main(String args[]) {

    FunctionalInterfaceExample obj1 = new FunctionalInterfaceExample() {
      @Override
      public void show() {
        System.out.println("One way to implement");
      }
    };

    FunctionalInterfaceExample obj2 = () -> System.out.println("Implement with Lambda expression");

    obj1.show();
    obj2.show();

    //THEORY
    //Types
    //1.Predicate-->boolean result
    //2.Consumer-->no result
    //3.Function-->both input and output
    //4.Supplier-->no input only supply

    //Predicate has only test() that returns boolean
    String name = "Theodoros";
    Predicate<String> checkLength = str -> str.length() > 5;
    System.out.println(name + " has more than 5 letters: " + checkLength.test(name));

    //Consumer only modifies data, does not return output
    Person person = new Person();
    Consumer<Person> consumer = t -> t.setName("consumer set name");
    consumer.accept(person);
    System.out.println(person.getName());

    //Function, both input and output
    Function<Double, String> functionTest = t -> t * 10 + " just testing";
    System.out.println(functionTest.apply(10.2));

    //Supplier, only output, no input
    Supplier<Double> getMathRandom = () -> Math.random() * 100;
    System.out.println("Supply a random number: " + getMathRandom.get());

  }


}

class Person {

  private String name;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}



