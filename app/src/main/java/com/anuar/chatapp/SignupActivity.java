package com.anuar.chatapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {

    private static final String TAG="SignupActivity";
    private FirebaseAuth mAuth;
    EditText emailEditText;
    EditText passwordEditText;
    EditText nameEditText;
    Button button;
    TextView textView;
    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(SignupActivity.this, MainActivity.class));
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailEditText=findViewById(R.id.email);
        passwordEditText=findViewById(R.id.password);
        nameEditText=findViewById(R.id.name);
        button=findViewById(R.id.login);
        textView=findViewById(R.id.textView2);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("users");
    }

    public void Login(View view) {
        startActivity(new Intent(SignupActivity.this,LoginActivity.class));
    }

    public void Signup(View view) {
        String email=emailEditText.getText().toString().trim();
        String password=passwordEditText.getText().toString().trim();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            createUser(user);

                            Intent intent=new Intent(SignupActivity.this, SignupActivity.class);
                            startActivity(intent);
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }
                    }
                });
    }

    private void createUser(FirebaseUser user) {
        User user1=new User();
        user1.setName(nameEditText.getText().toString().trim());
        user1.setEmail(user.getEmail());
        user1.setId(user.getUid());
        myRef.push().setValue(user1);
    }
}