package com.threads.examples;

import java.util.Scanner;

public class Processor {

  public void produce(int i) throws InterruptedException {
    synchronized (this) {
      System.out.println("Producer number " + i + " started");
      wait(); //releases the intrinsic lock for the next
      //synchronized method
      System.out.println("Resumed producer number " + i);
    }
  }

  public void consume() throws InterruptedException {
    //wait for producer to get the lock
    Thread.sleep(2000);
    Scanner scanner = new Scanner(System.in); //should be closed once done scanner.close()
    synchronized (this) {
      System.out.println("Waiting for the enter key in the consumer");
      scanner.nextLine();
      System.out.println("Enter key pressed");
      Thread.sleep(1000);
      notifyAll();//returns the lock to the thread that used the wait()
      //notifyAll for a number of threads
    }
  }

}
