package com.threads.examples;

public class VolatileExample extends Thread {

  private volatile boolean running = true;

  public void run() {
    while (running) {
      try {
        System.out.println("Thread says hello...");
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public void shutDown() {
    this.running = false;
  }
}
