// Week 3
// sestoft@itu.dk * 2015-09-09
package exercises07;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.Formatter.BigDecimalLayoutForm;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.checkerframework.checker.units.qual.Length;

public class TestWordStream {
  public static void main(String[] args) {
    String filename = "app/src/main/resources/english-words.txt";
    System.out.println(readWords(filename).count());

    //print the first 100 words
    System.out.println("Print the first 100 words:");
    readWords(filename).limit(100).forEach(w -> System.out.println(w));

    //print all words that have at least 22 letters
    System.out.println("Print all words that have at least 22 letters:");
    readWords(filename).filter(w -> (w.length() > 22)).forEach(w -> System.out.println(w));

    //print some words that have at least 22 letters
    System.out.println("Print some words that have at least 22 letters:");
    readWords(filename).filter(w -> (w.length() > 22)).skip(10).forEach(w -> System.out.println(w));

    //Find all palindromes and print them.
    System.out.println("Find all palindromes and print them:");
    long startTime = System.currentTimeMillis();
    readWords(filename).filter(w -> isPalindrome(w)).forEach(w -> System.out.println(w));
    long endTime = System.currentTimeMillis();
    System.out.printf("Running time for sequential: %d\n",endTime - startTime);

    //Find all palindromes and print them -- parallel version.
    System.out.println("Find all palindromes and print them:");
    startTime = System.currentTimeMillis();
    readWords(filename).parallel().filter(w -> isPalindrome(w)).forEach(w -> System.out.println(w));
    endTime = System.currentTimeMillis();
    System.out.printf("Running time for parallel: %d\n",endTime - startTime);

    //New version of readword stream from URL
    String url = "https://staunstrups.dk/jst/english-words.txt"; 
    System.out.println(readWordStream(url).count());

    //Use a stream pipeline that turns the stream of words into a stream of their lengths, 
    //to find and print the minimal, maximal and average word lengths.
    int maxLen = readWordStream(url).map(w -> w.length()).max(Comparator.comparing(Integer::valueOf)).get();
    int minLen = readWordStream(url).map(w -> w.length()).min(Comparator.comparing(Integer::valueOf)).get();

    int totalLength = readWordStream(url).map(w -> w.length()).reduce(0, (a,b) -> a+b);
    System.out.format("max: %d, min %d, average:%.2f%n",maxLen, minLen, (float)totalLength/235883);
  }

  public static Stream<String> readWords(String filename) {
    try {
      BufferedReader reader = new BufferedReader(new FileReader(filename));
      // TO DO: Implement properly
      return reader.lines();
    } catch (IOException exn) { 
      return Stream.<String>empty();
    }
  }

  public static boolean isPalindrome(String s) {
    // TO DO: Implement properly
    StringBuffer sb = new StringBuffer(s);
    sb.reverse();
    String newStr = new String(sb);
    return s.equals(newStr) ? true : false;
  }

  public static Map<Character,Integer> letters(String s) {
    Map<Character,Integer> res = new TreeMap<>();
    // TO DO: Implement properly
    return res;
  }

  public static Stream<String> readWordStream(String url) { 
    try {
      HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
      BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
      return reader.lines();
    } 
    catch (IOException exn) { 
      return Stream.<String>empty(); 
    }
    }
}
