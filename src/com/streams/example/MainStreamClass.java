package com.streams.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class MainStreamClass {

  public static void main(String args[]) throws IOException {
    //Source-->Filter-->Sotr-->Map-->Collect
    //Intermediate operations-->filter, map or sort-->return a stream
    //anyMatch(), distinct(), filter(), findFirst(), flatMap(), map(), skip(), sorted()

    //Terminal operations-->forEach, collect, reduce-->return void or non-stream result
    //forEach()-->applies only a specific function at all elements
    //collect()-->saves the elements into a collection
    //reduce-->count(), max(), min(), reduce(), summaryStatistics()

    //1. Integer Stream
    IntStream.range(1, 10).forEach(System.out::print);
    System.out.println();

    //2. Integer Stream with skip
    IntStream.range(-5, 10).skip(5).forEach(x -> System.out.print(x));
    System.out.println();

    //3. Integer Stream with sum
    System.out.println(IntStream.range(1, 10).sum());

    //4. Stream.of sorted and findFirst
    Stream.of("Theo", "Eli", "Liana").sorted().findFirst().ifPresent(System.out::print);
    System.out.println();

    //5. Stream from Array, sort, filter and print
    String[] names = {"Christos", "Liana", "Silia", "Stefanos", "Eli", "Theo"};
    //Stream.of(names).filter(x -> x.startsWith("S")).sorted().forEach(System.out::println);
    Arrays.stream(names).filter(x -> x.startsWith("S")).sorted().forEach(System.out::println);

    //6. Average of squares of an int Array
    Arrays.stream(new int[]{1, 2, 3, 4, 5}).map(t -> t * t).average().ifPresent(System.out::println);

    //7. Stream from List, filter and print
    List<String> people = Arrays.asList("Christos", "Liana", "Silia", "Stefanos", "Eli", "Theo");
    people.stream().map(o -> o.toLowerCase()).filter(t -> t.startsWith("s")).forEach(System.out::println);

    //8. Stream rows from text file, sort, filter and print
    Stream<String> bands = Stream.empty();
    try {
      System.out.println("Stream 8");
      System.out.println("------------------------");
      bands = Files.lines(Paths.get(System.getProperty("user.dir") + "\\src\\com\\streams\\example\\bands.txt"));
      bands.sorted().filter(x -> x.length() > 12).forEach(System.out::println);
    } catch (IOException e) {
      System.out.println("File not found");
      //to avoid memory licks
      bands.close();
    } finally {
      bands.close();
      System.out.println("Stream closed");
    }

    //9. Stream rows from text file and save to List
    try {
      System.out.println("Stream 9");
      System.out.println("------------------------");
      bands = Files.lines(Paths.get(System.getProperty("user.dir") + "\\src\\com\\streams\\example\\bands.txt"));
      List<String> bands2 = bands.sorted().filter(x -> x.contains("t")).collect(Collectors.toList());
      bands2.forEach(x -> System.out.println(x));
    } catch (IOException e) {
      System.out.println("File not found test");
      //to avoid memory licks
      bands.close();
    } finally {
      bands.close();
      System.out.println("Stream closed");
    }

    //10. Stream rows from CSV file and count
    Stream<String> rows1 = Stream.empty();
    try {
      System.out.println("Stream 10");
      System.out.println("------------------------");
      rows1 = Files.lines(Paths.get(System.getProperty("user.dir") + "\\src\\com\\streams\\example\\data.txt"));
      int rowCount = (int) rows1.map(x -> x.split(",")).filter(t -> t.length == 3).count();
      System.out.println("rowCount: " + rowCount);
    } catch (IOException e) {
      System.out.println("File not found");
      //to avoid memory licks
      bands.close();
    } finally {
      bands.close();
      System.out.println("Stream closed");
    }

    //11. Stream rows from CSV file, parse data from rows
    try {
      System.out.println("Stream 11");
      System.out.println("------------------------");
      rows1 = Files.lines(Paths.get(System.getProperty("user.dir") + "\\src\\com\\streams\\example\\data.txt"));
      rows1.map(x -> x.split(","))
          .filter(x -> x.length == 3)
          .filter(x -> Integer.parseInt(x[1]) > 15)
          .forEach(x -> System.out.println(x[0]));
    } catch (IOException e) {
      System.out.println("File not found");
      //to avoid memory licks
      bands.close();
    } finally {
      bands.close();
      System.out.println("Stream closed");
    }

    //12. Stream rows from CSV file, store fields in HashMap
    try {
      System.out.println("Stream 12");
      System.out.println("------------------------");
      rows1 = Files.lines(Paths.get(System.getProperty("user.dir") + "\\src\\com\\streams\\example\\data.txt"));
      Map<String, Integer> map = rows1.map(x -> x.split(","))
          .filter(x -> x.length == 3)
          .filter(x -> Integer.parseInt(x[1]) > 15)
          .collect(Collectors.toMap(x -> x[0], x -> Integer.parseInt(x[1])));
      map.keySet().stream().forEach(x -> System.out.println(x + " " + map.get(x)));
    } catch (IOException e) {
      System.out.println("File not found");
      //to avoid memory licks
      bands.close();
    } finally {
      bands.close();
      System.out.println("Stream closed");
    }

    //13. Reduction - sum
    System.out.println("Stream 13");
    System.out.println("------------------------");
    double total = Stream.of(7.3, 1.5, 4.8).reduce(0.0, (Double a, Double b) -> a + b);
    System.out.println("Total " + total);

    //14. Reduction - summary statistics
    System.out.println("Stream 14");
    System.out.println("------------------------");
    IntSummaryStatistics summary = IntStream.of(7, 2, 19, 88, 73, 4, 10).summaryStatistics();
    System.out.println("Summary " + summary);


  }

}
