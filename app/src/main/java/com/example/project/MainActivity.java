package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String name, password;

    EditText nameInput;
    EditText passwordInput;

    Button loginButton, registerButton;

    private ArrayList<User> userArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = (EditText) findViewById(R.id.editTextTextPersonName);
        passwordInput = (EditText) findViewById(R.id.editTextTextPassword);

        loginButton = (Button) findViewById(R.id.button);
        registerButton = (Button) findViewById(R.id.button2);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                password = passwordInput.getText().toString();

                User tempUser = new User(name, password);
                for(int i=0; i<userArrayList.size(); i++)
                {
                    tempUser = userArrayList.get(i);
                    if(tempUser.getUsername().equals(name))
                    {
                        if(tempUser.getPassword().equals(password))
                        {
                            showToast("Login successful");
                        }
                    }
                    else
                    {
                        showToast("Login fail");
                    }

                }
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = nameInput.getText().toString();
                password = passwordInput.getText().toString();

                User user = new User(name, password);
                userArrayList.add(user);
                showToast("User created successfully");
            }
        });
    }

    private void showToast(String text){
        Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
    }
}