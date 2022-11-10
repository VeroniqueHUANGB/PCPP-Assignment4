package exercises09;

import com.google.common.base.Stopwatch;

import java.awt.event.*;
import javax.swing.*;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.List;
import java.util.ArrayList;
/* This example is inspired by the StopWatch app in Head First. Android Development
   http://shop.oreilly.com/product/0636920029045.do
   Modified to Java, October 7, 2021 by Jørgen Staunstrup, ITU, jst@itu.dk
   Updated October 30, 2022*/

public class StopwatchN {
    public static void main(String[] args) { new StopwatchN(10); }

    final static JFrame f= new JFrame("Stopwatch");

    //Setting up the three streams for the Buttons and the display
    final static stopwatchUI myUI= new stopwatchUI(0, f);
    final static List<stopwatchUI> myUIList = new ArrayList<>();
    public StopwatchN(int n) {
        f.setBounds(0, 0, 220 * n, 220);
        f.setLayout(null);
        f.setVisible(true);
        for (int i = 0; i < n; i++){
            myUIList.add(new stopwatchUI(i * 220, f));
        }

        // Background Thread simulating a clock ticking every 1 second

        for (int i = 0; i < myUIList.size(); i++){
            stopwatchUI myUI= myUIList.get(i);
            new Thread() {
                @Override
                public void run() {
                    try {
                        while ( true ) {
                            TimeUnit.MILLISECONDS.sleep(100);
                            myUI.updateTime();
                        }
                    } catch (java.lang.InterruptedException e) { System.out.println(e.toString());   }
                }
            }.start();

        }
    }
}
