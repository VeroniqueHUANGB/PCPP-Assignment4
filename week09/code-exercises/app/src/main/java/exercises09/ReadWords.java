package exercises09;

import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ReadWords {
    public static void main(String[] args) { new ReadWords(); }

    public ReadWords(){

        readWords.subscribe(display);

        System.out.println(readWords.take(100));
    }

    // 9.4.1
    Observable<String> readWords = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> s) throws Exception {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("./english-words.txt"));
                String cur = reader.readLine();
                while (cur!= null) {
                    s.onNext(cur);
                    cur = reader.readLine();
                }
                reader.close();
            } catch (IOException e) {
                System.out.println(e.toString());
            }

        }
    });

    // 9.4.2
    final Observer<String> display = new Observer<String>() {
        @Override
        public void onSubscribe(Disposable d) {  }
        @Override
        public void onNext(String value) {
            System.out.println(value);
        }
        @Override
        public void onError(Throwable e) {System.out.println("onError: "); }
        @Override
        public void onComplete() { System.out.println("onComplete: All Done!");   }

    };

}