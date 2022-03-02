package com.recursion.examples;

import java.util.Arrays;
import java.util.Random;

public class MainRecursionClass {

  private static Random random = new Random();
  private static int countMerge = 0;

  public static void main(String args[]) {
    //1. fibonacci
    //System.out.println(fibonacci(7));

    //2. mergeSort
    int[] array = random.ints(0, 10).limit(10).toArray();
    Arrays.stream(array).forEach(x -> System.out.print(x + ", "));
    array = mergeSort(array);
    System.out.println();
    System.out.println("After");
    Arrays.stream(array).forEach(x -> System.out.print(x + ", "));

  }

  private static int fibonacci(int k) {
    if (k <= 1) {
      return k;
    }
    return (fibonacci(k - 1) + fibonacci(k - 2));
  }

  private static int[] mergeSort(int[] array) {
    if (array.length == 1) {
      return array;
    }
    int halfLength = array.length / 2;
    int[] leftArray = new int[halfLength];
    for (int i = 0; i < halfLength; i++) {
      leftArray[i] = array[i];
    }
    int[] rightArray = new int[array.length - halfLength];
    for (int i = halfLength; i < array.length; i++) {
      rightArray[i - halfLength] = array[i];
    }
    mergeSort(leftArray);
    mergeSort(rightArray);
    return merge(array, leftArray, rightArray);
  }

  private static int[] merge(int[] array, int[] leftArray, int[] rightArray) {

    int leftSize = leftArray.length;
    int rightSize = rightArray.length;
    int i = 0, j = 0, k = 0;

    while (i < leftSize && j < rightSize) {
      if (leftArray[i] < rightArray[j]) {
        array[k] = leftArray[i];
        i++;
      } else {
        array[k] = rightArray[j];
        j++;
      }
      k++;
    }

    while (i < leftSize) {
      array[k] = leftArray[i];
      i++;
      k++;
    }
    while (j < rightSize) {
      array[k] = rightArray[j];
      j++;
      k++;
    }
    return array;
  }

}
