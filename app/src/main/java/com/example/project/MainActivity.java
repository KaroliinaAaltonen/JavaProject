package com.example.project;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
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

    Button loginButton, registerButton;

    private final ArrayList<User> userArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameInput = findViewById(R.id.editTextTextPersonName);
        passwordInput = findViewById(R.id.editTextTextPassword);

        loginButton = findViewById(R.id.button);
        registerButton = findViewById(R.id.button2);

        loginButton.setOnClickListener(v -> {
            name = nameInput.getText().toString();
            password = encryptPassword(passwordInput.getText().toString());

            if (checkIfUserExists(true)) showToast("Login successful!");
        });

        registerButton.setOnClickListener(v -> {
            name = nameInput.getText().toString();
            if (checkPassword(passwordInput.getText().toString())) {
                password = encryptPassword(passwordInput.getText().toString());
                User user = new User(name, password);
                if (!checkIfUserExists(false)){userArrayList.add(user); showToast("User created successfully");}
            }
        });
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
    // Checks if the given password follows principals of a strong password
    private boolean checkPassword(String text){
        boolean hasUpperCase = false, hasSpecial = false, hasNumber = false;
        Set<Character> set = new HashSet<>(Arrays.asList('!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '+'));

        char[] arr = text.toCharArray();
        if (arr.length < 12) {
            showToast("Password should be at least 12 characters long!");
            return false;
        }

        for (char c : arr) {
            if (Character.isUpperCase(c)) hasUpperCase = true;
            if (set.contains(c)) hasSpecial = true;
            if (Character.isDigit(c)) hasNumber = true;
        }
        if (hasUpperCase && hasSpecial && hasNumber) return true;
        else {showToast("Password should contain at least one of each: upper case letter, special character and number"); return false;}


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
}