package com.example.gcm;

import android.widget.EditText;

public class LoginActivity {



    public class LoginActivity extends AppCompatActivity {
        private FirebaseAuth firebaseAuth;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            firebaseAuth = FirebaseAuth.getInstance();

            // Handle login or registration button click
            Button buttonLogin = findViewById(R.id.buttonLogin);
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Get the entered email and password
                    EditText editTextEmail = findViewById(R.id.editTextEmail);
                    EditText editTextPassword = findViewById(R.id.editTextPassword);
                    String email = editTextEmail.getText().toString();
                    String password = editTextPassword.getText().toString();

                    // Use Firebase Authentication to sign in or register the user
                    firebaseAuth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // User login or registration successful
                                        // Proceed to the main activity
                                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                        finish();
                                    } else {
                                        // Handle login or registration failure
                                        Toast.makeText(LoginActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
            });
        }
    }


}
