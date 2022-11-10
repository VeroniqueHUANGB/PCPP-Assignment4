package exercises09;

import java.awt.event.*;
import javax.swing.*;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

class StopwatchObservable {

    private JFrame lf;
    final private JButton startButton= new JButton("Start");
    final private JButton stopButton= new JButton("Stop");
    final private JButton resetButton= new JButton("Reset");
    final private JTextField tf= new JTextField();

    final private String allzero = "0:00:00";
    private SecCounter lC= new SecCounter(0, false, tf);

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

    public StopwatchObservable(int x, JFrame jF) {
        int lx= x+50; lf= jF;
        tf.setBounds(lx, 10, 60, 20);
        tf.setText(allzero);

        startButton.setBounds(lx, 50, 95, 25);
        rxStart.subscribe(display);

        stopButton.setBounds(lx, 90, 95, 25);
        rxStop.subscribe(display);

        resetButton.setBounds(lx, 130, 95, 25);
        rxReset.subscribe(display);

        // set up user interface
        lf.add(tf);
        lf.add(startButton);
        lf.add(stopButton);
        lf.add(resetButton);
    }

    final Observable<Integer> rxStart= Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
            startButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ee){
                    lC.setRunning(true);
                }
            });
        }
    });

    final Observable<Integer> rxStop= Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
            stopButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ee){
                    lC.setRunning(false);
                }
            });
        }
    });

    final Observable<Integer> rxReset= Observable.create(new ObservableOnSubscribe<Integer>() {
        @Override
        public void subscribe(ObservableEmitter<Integer> e) throws Exception {
            resetButton.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ee){
                    lC.reset();
                }
            });
        }
    });


    final Observer<Integer> display= new Observer<Integer>() {
        @Override
        public void onSubscribe(Disposable d) {  }
        @Override
        public void onNext(Integer value) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (java.lang.InterruptedException e) { System.out.println(e.toString()); };
            System.out.println("Pushed");
        }
        @Override
        public void onError(Throwable e) {System.out.println("onError: "); }
        @Override
        public void onComplete() { System.out.println("onComplete: All Done!");   }
    };
}
