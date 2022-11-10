## 9.1

### 9.1.1

```java
// Stopwatch.java
TimeUnit.MILLISECONDS.sleep(100);

// StopwatchUI.java
public synchronized void updateTime(){
    int seconds= lC.incr();
    int newSeconds = seconds / 10;
    // Potentila race condition !!!
    if ( seconds>= 0 ) {
      int hours= seconds/3600;
      int minutes= (seconds%3600)/60;
      int secs= seconds%60;
      int newSecs = newSeconds%10;
      String time= String.format(Locale.getDefault(),	"%d:%02d:%02d:%d", hours, minutes, newSecs, secs);
      tf.setText(time);
    }
  };
```

### 9.1.2

```java
// StopwatchUI.java
public synchronized void updateTime()
```

### 9.1.3

```java
Stopwatch2.java
```

### 9.1.4

```java
StopwatchN.java
```

## 9.2

### 9.2.1

> Task :app:TestRx.main()
> Hello World!



### 9.2.2

> Task :app:ObservableTester.main()
> Hello World



### 9.2.3

> Task :app:ScheduleTester.main()
> Processing Thread pool-1-thread-1
> Processing Thread pool-3-thread-1
> Receiver Thread pool-1-thread-1, Item length 1
> Receiver Thread pool-1-thread-1, Item length 2
> Processing Thread pool-4-thread-1
> Receiver Thread pool-4-thread-1, Item length 3



## 9.3

### 9.3.1

```java
StopwatchRx.java
```

### 9.3.2

```java
StopwatchObservable.java
```

## 9.4

### 9.4.1

```java
ReadWords.java
```

### 9.4.2

```java
ReadWords.java
```

### 9.4.3

```java
ReadWords.java
```

