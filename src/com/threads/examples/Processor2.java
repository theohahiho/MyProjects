package com.threads.examples;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Processor2 {

  private static int LIMIT = 10;
  private LinkedList<Integer> linkedList = new LinkedList<>();
  private Object lock = new Object();

  public void produce() throws InterruptedException {
    Random random = new Random();
    int value = 0;
    System.out.println("Producer started");
    while (true) {
      synchronized (lock) {
        if (linkedList.size() == LIMIT) {
          System.out.println("LinkedList limit reached, wait to be unloaded");
          lock.wait();
        }
        this.linkedList.add(value++);
        lock.notify();
      }
      Thread.sleep(random.nextInt(500));
    }
  }

  public void consume() throws InterruptedException {
    Random random = new Random();
    while (true) {
      synchronized (lock) {
        while (linkedList.size() == 0) {
          lock.wait();
        }
        System.out.println("List size " + linkedList.size());
        int value = linkedList.removeFirst();
        System.out.println("Value removed: " + value);
        lock.notify();
      }
      Thread.sleep(random.nextInt(1000));

    }
  }

}
