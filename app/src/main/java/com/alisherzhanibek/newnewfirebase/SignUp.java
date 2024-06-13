package com.alisherzhanibek.newnewfirebase;

import static android.app.ProgressDialog.show;

import static java.lang.System.in;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    Button signUPBTN;
    private EditText nameEditText;

    private EditText emailEditText;
    private EditText passwordEditText;
    private static final String TAG = "SignInActivity";
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        init();

        signUPBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUser(emailEditText.getText().toString(), passwordEditText.getText().toString());

                Intent intent = new Intent(SignUp.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }
    public void init(){
        signUPBTN = findViewById(R.id.singUpBTN);
        nameEditText = findViewById(R.id.userName);
        emailEditText = findViewById(R.id.userEmail);
        passwordEditText = findViewById(R.id.userPassword);
        auth = FirebaseAuth.getInstance();

//        String email = emailEditText.getText().toString();
//        String password = passwordEditText.getText().toString();
//        String name = nameEditText.getText().toString();
    }
    public void signUser(String email, String password){
        auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Intent intent = new Intent(SignUp.this, MainActivity.class);
                            startActivity(intent);
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUp.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}