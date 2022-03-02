package com.threads.examples;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LocksExample {

  private Random random = new Random();
  private List<Integer> list1 = new ArrayList<>();
  private List<Integer> list2 = new ArrayList<>();

  Object lock1 = new Object();
  Object lock2 = new Object();


  private void stageOne() {
    synchronized (lock1) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      list1.add(random.nextInt(100));
    }
  }

  // Synchronized signature in a method, will acquire the intrinsic lock of the class object.
  // In order for the stageOne() and the stageTwo() to be executable concurrently by different threads
  // we need to add a synchronized block inside the two methods with two different lock objects
  //private synchronized void stageTwo() {
    private void stageTwo() {
    synchronized (lock2) {
      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      list2.add(random.nextInt(100));
    }
  }

  public void process() {
    System.out.println("Locks process started");
    long startTime = System.currentTimeMillis();

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 1000; i++) {
          stageOne();
          stageTwo();
        }
      }
    });
    Thread t2 = new Thread(() -> {
      for (int i = 0; i < 1000; i++) {
        stageOne();
        stageTwo();
      }
    });

    t1.start();
    t2.start();
    try {
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    long endTime = System.currentTimeMillis();

    System.out.println("Time passed: " + (endTime - startTime));
    System.out.println("List1 size is " + list1.size());
    System.out.println("List2 size is " + list2.size());
  }
}
