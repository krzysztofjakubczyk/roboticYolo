package com.example.watki;

import android.widget.TextView;

public class FakeAIWork {

    private TextView output;

    public FakeAIWork(TextView output) {
        this.output = output;
    }

    public void start() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // =========================
            // runOnUiThread – nie działa bez referencji do Activity
            // =========================
//             output.runOnUiThread(() -> output.setText("RunOnUiThread")); // ❌ NIE KOMPILE

            // =========================
            // View.post – działa, wystarczy widok
            // =========================
            output.post(() -> output.setText("View.post() działa!"));

        }).start();
    }
}

