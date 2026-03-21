package com.example.watki;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button myButton;

    Button nextWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.statusText);
        myButton = findViewById(R.id.button);
        nextWindow = findViewById(R.id.nextWindow);

        nextWindow.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

        myButton.setOnClickListener(v -> {

            textView.setText("Rozpoczynam zadanie...");

            /*
            =====================================================
            DEMO 1 – blokada UI (brak wątku)
            =====================================================
            UI thread jest zablokowany → aplikacja się zawiesza
             */

            /*
            try {
                Thread.sleep(5000); // UI zamrożone przez 5s
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            textView.setText("Dane pobrane");
            */


            /*
            =====================================================
            DEMO 2 – wątek, ale błędna aktualizacja UI
            =====================================================
            Próba zmiany TextView z innego wątku → crash
             */

            /*
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                textView.setText("Dane pobrane"); //
            }).start();
            */


            /*
            =====================================================
            DEMO 3 – poprawne runOnUiThread()
            =====================================================
            UI aktualizujemy bezpiecznie
             */


            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                runOnUiThread(() -> textView.setText("Dane pobrane"));
            }).start();



            /*
            =====================================================
            DEMO 4 – View.post()
            =====================================================
            Zadanie wykonane na wątku głównym przez TextView
             */


//            new Thread(() -> {
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                textView.post(() -> textView.setText("Dane pobrane (post)"));
//            }).start();



            /*
            =====================================================
            DEMO 5 – View.postDelayed()
            =====================================================
            Wywołanie po opóźnieniu np. symulacja streamu / AI
             */

//            new Thread(() -> {
//                try {
//                    Thread.sleep(1000); // symulacja obliczeń
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                textView.postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        textView.setText("Dane pobrane po 2s (postDelayed)");
//                    }
//                }, 2000);
//            }).start();

        });
    }
}
