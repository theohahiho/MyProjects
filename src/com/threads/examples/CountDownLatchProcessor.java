package com.threads.examples;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchProcessor implements Runnable {

  private CountDownLatch latch;

  public CountDownLatchProcessor(CountDownLatch latch) {
    this.latch = latch;
  }

  public void run() {
    System.out.println("Started with latch: " + latch.getCount());

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
    }
    latch.countDown();

    System.out.println("Completed with latch: " + latch.getCount());
  }
}
