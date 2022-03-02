package com.threads.examples;

public class ExecutorProcessor implements Runnable {

  private int id;

  public ExecutorProcessor(int id) {
    this.id = id;
  }

  public void run() {
    System.out.println("Starting id: " + id);

    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
    }

    System.out.println("Completed id: " + id);
  }
}
