package com.interview.chittoo;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FirebaseUser user;
    private ActivityResultLauncher<Intent> signInLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        signInLauncher = registerForActivityResult(
                new FirebaseAuthUIActivityResultContract(),
                this::onSignInResult
        );

        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            openNextAct();
        } else {
           signIn();
        }
        findViewById(R.id.button2).setOnClickListener(v -> signIn());
    }

    private void onSignInResult(FirebaseAuthUIAuthenticationResult result) {
        if (result.getResultCode() == RESULT_OK) {
            user = FirebaseAuth.getInstance().getCurrentUser();
            openNextAct();
            Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Signed in failed", Toast.LENGTH_SHORT).show();
        }
    }

    private void signIn(){
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        Intent signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build();
        signInLauncher.launch(signInIntent);
    }

    private void openNextAct(){
        Intent intent = new Intent(this, TabActivity.class);
        startActivity(intent);
        finish();
    }
}