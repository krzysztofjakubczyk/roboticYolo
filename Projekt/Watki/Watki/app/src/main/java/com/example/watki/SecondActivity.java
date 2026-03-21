package com.example.watki;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SecondActivity extends AppCompatActivity {

    TextView textView1, textView2, textView3;
    Button buttonRun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // widoki do aktualizacji
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        TextView FakeAIWorker = findViewById(R.id.fakeAIWorker);
        FakeAIWork fakeAIWork = new FakeAIWork(FakeAIWorker);
        fakeAIWork.start();
        buttonRun = findViewById(R.id.buttonRun);

        buttonRun.setOnClickListener(v -> {

            // symulacja "AI / przetwarzania danych" w osobnym wątku
            new Thread(() -> {

                for (int i = 1; i <= 5; i++) {
                    String msg = "Klatka " + i;

                    // =====================
                    // runOnUiThread – trzeba użyć Activity
                    // =====================
                    runOnUiThread(() -> textView1.setText("runOnUiThread: " + msg));

                    // =====================
                    // View.post – każdy widok aktualizuje się sam
                    // =====================
                    textView2.post(() -> textView2.setText("post(): " + msg));
                    textView3.postDelayed(() -> textView3.setText("postDelayed(): " + msg), 1000);

                    try {
                        Thread.sleep(1500); // symulacja ciężkiej pracy
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }).start();

        });
    }
}
