package com.siddheswar.newearthnews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.CubeGrid;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {
    EditText UserNameLogin, PasswordLogin;
    Button btnLogin;
    TextView gateway;
    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
//
//        PasswordLogin = findViewById(R.id.EtPassword);
//        UserNameLogin = findViewById(R.id.EtUserName);
        btnLogin = findViewById(R.id.btnLogin);
        gateway = findViewById(R.id.gateway);
       progressBar = (ProgressBar)findViewById(R.id.spin_kit);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sprite doubleBounce = new CubeGrid();
                progressBar.setIndeterminateDrawable(doubleBounce);
                progressBar.setVisibility(View.VISIBLE);

                /**
                 * Login an already registered user into the application.
                 * if the the credentials match it logs in else it throws
                 * "Authentication Failed"
                 */
                String email = UserNameLogin.getText().toString();
                String password = PasswordLogin.getText().toString();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                   Intent i = new Intent(Login.this, Genres.class);
                                   startActivity(i);
                                } else {
                                    Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                                }


                            }
                        });
            }
        });


        //gateway for signup activity from login activity
        gateway.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, SignUp.class);
                startActivity(i);
            }
        });
    }
}