package com.threads.examples;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class MainThreadClass {

  private int synchronizedCount = 0;

  public static void main(String args[]) throws InterruptedException {

    //1. Volatile
    //executeVolatileExample();

    //2. Synchronized
    //MainClass main = new MainClass();
    //main.executeSynchronizedExample();

    //3. Locks
    //LocksExample locksExample = new LocksExample();
    //locksExample.process();

    //4. ThreadPool
    //implementThreadPool();

    //5. CountDownLatch
    //implementCountDownLatch();

    //6. Producer-Consumer pattern, blocking queues
    //For example, receive sms, buffer and resend them
    //implementProducerConsumer();

    //7. Wait-notify
    //waitAndNotify();

    //8. Wait-notify linkedList
    //waitAndNotify2();

    //9. Reentrant lock
    testReentrantLock();
  }

  private static void testReentrantLock() throws InterruptedException {
    ReentrantExample reentrantExample = new ReentrantExample();
    Thread t1 = new Thread(() -> {
      try {
        reentrantExample.firstThread();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread t2 = new Thread(() -> {
      try {
        reentrantExample.secondThread();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
    t1.start();
    t2.start();
    t1.join();
    t2.join();

    reentrantExample.finish();
  }

  private static void waitAndNotify2() throws InterruptedException {
    Processor2 processor = new Processor2();
    Thread t1 = new Thread(() -> {
      try {
        processor.produce();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread t2 = new Thread(() -> {
      try {
        processor.consume();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    t1.start();
    t2.start();

    t1.join();
    t2.join();

  }

  private static void waitAndNotify() throws InterruptedException {
    Processor processor = new Processor();
    Thread t1 = new Thread(() -> {
      try {
        processor.produce(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread t2 = new Thread(() -> {
      try {
        processor.consume();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    Thread t3 = new Thread(() -> {
      try {
        processor.produce(2);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    t1.start();
    t2.start();
    t3.start();

    t1.join();
    t2.join();
    t3.join();

  }

  private static void implementProducerConsumer() throws InterruptedException {
    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    Thread t1 = new Thread(() -> produceRandomNumbers(queue));
    Thread t2 = new Thread(() -> consumeRandomNumbers(queue));

    t1.start();
    t2.start();

    t1.join();
    t2.join();
  }

  private static void produceRandomNumbers(BlockingQueue<Integer> queue) {
    Random random = new Random();

    while (true) {
      try {
        if (queue.size() < 10) {
          Thread.sleep(200);
          queue.add(random.nextInt(100));
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private static void consumeRandomNumbers(BlockingQueue<Integer> queue) {
    Random random = new Random();

    while (true) {
      try {
        Thread.sleep(100);
        if (random.nextInt(10) == 0) {
          int number = queue.take();
          System.out.println("Number taken: " + number + " and size of queue: " + queue.size());
        }
      } catch (InterruptedException e) {
        e.printStackTrace();
      }


    }
  }

  private static void implementCountDownLatch() {
    //Synchronized class
    CountDownLatch latch = new CountDownLatch(3);
    ExecutorService executor = Executors.newFixedThreadPool(2);

    for (int i = 0; i < 3; i++) {
      executor.submit(new CountDownLatchProcessor(latch));
    }

    executor.shutdown();

    try {
      latch.await();
      executor.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("All tasks completed");

  }

  private static void implementThreadPool() {
    ExecutorService service = Executors.newFixedThreadPool(2);

    long timeStart = System.currentTimeMillis();
    for (int i = 0; i < 5; i++) {
      service.submit(new ExecutorProcessor(i + 1));
    }

    service.shutdown();
    try {
      service.awaitTermination(1, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("All tasks completed");
    System.out.println("Time: " + (System.currentTimeMillis() - timeStart));

  }

  private void executeSynchronizedExample() {

    Thread t1 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 1000; i++) {
          incrementCount();
        }
      }
    });

    Thread t2 = new Thread(new Runnable() {
      @Override
      public void run() {
        for (int i = 0; i < 1000; i++) {
          incrementCount();
        }
      }
    });

    t1.start();
    t2.start();

    try {
      // Use join so that the main Thread will wait for t1, t2
      t1.join();
      t2.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Count is " + synchronizedCount);

  }

  private synchronized void incrementCount() {
    synchronizedCount++;
    //OR count.incrementAndGet(); where AtomicInteger count
  }

  private static void executeVolatileExample() {
    System.out.println("Volatile process started");
    long startTime = System.currentTimeMillis();

    // Volatile boolean variables should be used as flags for other threads
    // since their values are saved directly to the main memory.
    // All the other variables assigned a value before the volatile variable assignation
    // will have updated values in the other threads once the volatile variable is being read.
    // The above concept is known also as 'happens before...'
    VolatileExample volatileExample = new VolatileExample();
    volatileExample.start();

    // press enter to continue
    Scanner scanner = new Scanner(System.in);
    scanner.nextLine();

    volatileExample.shutDown();
    long endTime = System.currentTimeMillis();

    System.out.println("Time passed: " + (endTime - startTime));
  }

}
