### Exercise 07

##### Exercise 7.2

- (Mandatory) Compile and run PrimeCountingPerf.java. Record the result in a text file.

  | Msg        | Mean         | sDev      | Count |
  | ---------- | ------------ | --------- | ----- |
  | Sequential | 9956869.8 ns | 453961.31 | 32    |

- (Mandatory)Fill in the Java code using a stream for counting the number of primes (in the range: 2..range). Record the

  result in a text file.

  | Msg       | Mean          | sDev       | Count |
  | --------- | ------------- | ---------- | ----- |
  | IntStream | 11651079.2 ns | 1948626.03 | 32    |

- Add code to the stream expression that prints all the primes in the range 2..range. To test this program reduce range to a small number e.g. 1000.

  We record all the prime numbers smaller than 1000 in app/src/main/resource/PrimeNumbers.txt

- Fill in the Java code using the intermediate operation parallel for counting the number of primes (in the range: 2..range). Record the result in a text file.

  | Msg      | Mean         | sDev      | Count |
  | -------- | ------------ | --------- | ----- |
  | Parallel | 5570944.2 ns | 325533.33 | 64    |

- Add another prime counting method using a parallelStream for counting the number of primes (in the range: 2..range). Measure its performance using Mark7 in a way similar to how we measured the performance of the other three ways of counting primes.

  | Msg              | Mean         | sDev       | Count |
  | ---------------- | ------------ | ---------- | ----- |
  | ParallelStrea,=m | 5746143.5 ns | 1002697.96 | 32    |



##### Exercise 7.2

- Starting from the TestWordStream.java file, complete the readWords method and check that you can read the file as a stream and count the number of English words in it. For the english-words.txt file on the course homepage the result should be 235,886.
- Write a stream pipeline to print the first 100 words from the file.
- Write a stream pipeline to find and print all words that have at least 22 letters.
- Write a stream pipeline to find and print some word that has at least 22 letters.

- Write a method boolean isPalindrome(String s) that tests whether a word s is a palindrome: a word that is the same spelled forward and backward. Write a stream pipeline to find all palindromes and print them.

- Make a parallel version of the palindrome-printing stream pipeline. Is it possible to observe whether it is faster or slower than the sequential one?

  I record the start time and end time for both sequential one and parallel one. For sequential one, the running time is 0.199s, and for parallel one the running time is 0.07s. So the parallel version is faster than the sequential one.

  All the above questions finished, you can see the code from app/src/main/java/exercises07/TestWordStream.java

- Make a new version of the method readWordStream which can fetch the list of words from the internet. There is a (slightly modified) version of the word list at this URL: https://staunstrups.dk/jst/english-words.txt. Use this version of readWordStream to count the number of words (similarly to question 7.2.1). Note, the number of words is *not* the same in the two files !!

  So after running, the number of words is 235883, 3 words less than the file.

- Use a stream pipeline that turns the stream of words into a stream of their lengths, to find and print the minimal, maximal and average word lengths.
   Hint: There is a simple solution using an operator examplified on p. 141 of Java Precisely (included in the readings for this week).

  | Max     | 24   |
  | ------- | ---- |
  | Min     | 1    |
  | Average | 9.57 |

  