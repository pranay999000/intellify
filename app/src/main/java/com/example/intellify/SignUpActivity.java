package com.example.intellify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {
    EditText emailSign;
    EditText passwordSign;
    Button signUpSign;
    Button loginSign;

    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        setTitle("Sign Up");

        firebaseAuth = FirebaseAuth.getInstance();

        emailSign = findViewById(R.id.emailSignId);
        passwordSign = findViewById(R.id.passwordSignId);

        emailSign.addTextChangedListener(watcher);
        passwordSign.addTextChangedListener(watcher);

        signUpSign = findViewById(R.id.signUpSignId);
        loginSign = findViewById(R.id.loginSignId);

        progressBar = findViewById(R.id.signUpProgress);

        signUpSign.setEnabled(false);

        signUpSign.setOnClickListener(v -> {
            progressBar.setVisibility(View.VISIBLE);

            String email = emailSign.getText().toString().trim();
            String password = passwordSign.getText().toString().trim();

            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Intent mainIntent = new Intent(SignUpActivity.this, MainActivity.class);
                        progressBar.setVisibility(View.GONE);
                        startActivity(mainIntent);
                        finish();
                    } else {
                        Toast.makeText(SignUpActivity.this, "Please try again!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                        passwordSign.setText("");
                    }
                }
            });
        });

        loginSign.setOnClickListener(v -> {
            Intent loginIntent = new Intent(SignUpActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        });
    }

    private final TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if(emailSign.getText().toString().trim().length() == 0 || passwordSign.getText().toString().trim().length() ==0) {
                signUpSign.setEnabled(false);
            } else {
                signUpSign.setEnabled(true);
            }
        }
    };
}
