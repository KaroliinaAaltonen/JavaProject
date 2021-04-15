package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    String name;
    byte[] password;

    EditText nameInput;
    EditText passwordInput;

    Button loginButton;
    TextView registerButton;

    private final ArrayList<User> userArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.editTextTextPersonName);
        passwordInput = findViewById(R.id.editTextTextPassword);

        loginButton = findViewById(R.id.button);
        registerButton = findViewById(R.id.textRegister);


        loginButton.setOnClickListener(v -> {
            name = nameInput.getText().toString();
            System.out.println(name);

            if (checkIfUserExists(true)) nextActivityLogin();
        });

        registerButton.setOnClickListener(v -> {
            nextActivityRegister();
        });
    }

    private void nextActivityLogin(){
        Intent intent = new Intent(MainActivity.this, MainActivity2.class);
        this.startActivity(intent);
    }

    private void nextActivityRegister(){
        Intent intent = new Intent(MainActivity.this, MainActivity3.class);
        this.startActivity(intent);
    }

    private void showToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }

    private boolean checkIfUserExists(boolean isLogin){
        for(int i=0; i<userArrayList.size(); i++)
        {
            User tempUser = userArrayList.get(i);
            if(tempUser.getUsername().equals(name))
            {
                if(Arrays.equals(tempUser.getPassword(), password) && isLogin)
                {
                    return true;
                }else
                {
                    if(isLogin) {showToast("Login failed: password is wrong!"); return false;}
                    else if (!isLogin) {showToast("User already exists!"); return true;}
                }
            }
            else {
                if(isLogin) {showToast("Login failed: user does not exist!"); return false;}
                else if(!isLogin) return false;
            }
        }
        Log.e("Error:", "Something did not go well in checkIfUserExists");
        return false;
    }
}