package com.interview.chittoo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AboutYouActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_you);

        TextView timerText = findViewById(R.id.textView_timer);
        EditText editText = findViewById(R.id.editTextAbout);
        Button button = findViewById(R.id.button4);

        new CountDownTimer(31000, 1000) {
            public void onTick(long millisUntilFinished) {
                timerText.setText(getString(R.string.timerText, millisUntilFinished / 1000));
            }

            public void onFinish() {
                timerText.setText(R.string.timeup);
                editText.setEnabled(false);
                button.setVisibility(View.VISIBLE);
            }
        }.start();

        button.setOnClickListener(v -> finish());
    }
}