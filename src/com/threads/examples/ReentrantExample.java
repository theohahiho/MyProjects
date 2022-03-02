package com.threads.examples;

import java.util.Scanner;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantExample {

  private int count = 0;
  private Lock lock = new ReentrantLock();
  private Condition cond = lock.newCondition();

  public void firstThread() throws InterruptedException {
    lock.lock();
    System.out.println("Thread 1 is waiting");
    cond.await();
    System.out.println("Thread 1 can now increment the count");

    try {
      incrementCount();
    } finally {
      lock.unlock();
    }

  }

  public void secondThread() throws InterruptedException {
    Thread.sleep(1000);

    lock.lock();
    System.out.println("Thread 2 picked up and waits for enter key to be pressed");
    new Scanner(System.in).nextLine();//wait for key enter
    cond.signal();//is like the lock.notify for a normal lock and should always be
    //followed by lock.unlock() so that the other threads pick up the lock
    try {
      System.out.println("Enter key pressed and the count increments for thread 2");
      incrementCount();
      System.out.println("Thread 2 counted to " + count);

    } finally {
      lock.unlock();
    }
  }

  public void finish() {
    System.out.println("Final count is: " + count);
  }

  private void incrementCount() {
    for (int i = 0; i < 10000; i++) {
      count++;
    }
  }

}
