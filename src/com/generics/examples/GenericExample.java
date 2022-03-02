package com.generics.examples;

import java.util.ArrayList;
import java.util.List;

public class GenericExample<T, Z extends String> {

  T genericObject;
  Z description;
  List<T> genericList = new ArrayList<T>();

  public GenericExample(T genericObject, Z description) {
    this.genericObject =  genericObject;
    this.description = description;
  }

  public void printGenericObject() {
    System.out.println("Printing the generic object: " + genericObject);
    System.out.println("of type: " + genericObject.getClass().getSimpleName());
  }

  public Z getDescription(){
    return this.description;
  }

  public void addObjectToList(T genericObj){
      genericList.add(genericObj);
  }

  public List<T> getGenericList(){
    return this.genericList;
  }

}
