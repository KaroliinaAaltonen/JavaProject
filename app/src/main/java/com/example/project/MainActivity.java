package com.example.project;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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

    public static ArrayList<User> userArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();

        nameInput = findViewById(R.id.editTextTextPersonName);
        passwordInput = findViewById(R.id.editTextTextPassword);

        loginButton = findViewById(R.id.button);
        registerButton = findViewById(R.id.textRegister);


        loginButton.setOnClickListener(v -> {
            name = nameInput.getText().toString();
            System.out.println(name);
            password = encryptPassword(passwordInput.getText().toString());

            if (checkIfUserExists()) nextActivityLogin();
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

    private boolean checkIfUserExists(){
        for(int i=0; i<userArrayList.size(); i++)
        {
            User tempUser = userArrayList.get(i);
            if(tempUser.getUsername().equals(name))
            {
                if(Arrays.equals(tempUser.getPassword(), password))
                {
                    return true;
                }else
                {
                    showToast("Login failed: password is wrong!"); return false;
                }
            }
        }

        showToast("Login failed: user does not exist!");
        return false;
    }
    // Encrypts the given password using SHA-512 + Salt.
    private byte[] encryptPassword(String text){
        String salt = "Ö/=&@$¤ä";

        byte[] inputData = (text + salt).getBytes();
        byte[] outputData = new byte[0];

        try {
            outputData = PasswordEncryption.encryptSHA(inputData, "SHA-512");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return  outputData;
    }

    private void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("user list", null);
        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        userArrayList = gson.fromJson(json, type);

        if (userArrayList == null){
            userArrayList = new ArrayList<>();
        }
    }
}