package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MainActivity3 extends AppCompatActivity {

    EditText nameInput;
    EditText passwordInput;
    EditText passwordRepeatInput;
    EditText birthdayInput;
    EditText heightInput;
    EditText weightInput;

    private Spinner spinnerGender;
    private Spinner spinnerSmoking;
    private Spinner spinnerDiet;
    Button continueButton;

    String name;
    byte[] password;
    String passwordRepeat;
    String birthday;
    float height=0;
    float weight=0;
    String dietaryInfo;
    String smokingInfo;
    String gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        nameInput = findViewById(R.id.editTextPreferredName);
        passwordInput = findViewById(R.id.editTextTextPasswordRegister);
        passwordRepeatInput = findViewById(R.id.editTextTextPasswordRegisterRepeat);
        birthdayInput = findViewById(R.id.editTextBirthday);
        heightInput = findViewById(R.id.editTextHeight);
        weightInput = findViewById(R.id.editTextWeight);

        continueButton = findViewById(R.id.buttonContinue);

        spinnerGender = findViewById(R.id.spinner);
        spinnerSmoking = findViewById(R.id.spinner2);
        spinnerDiet = findViewById(R.id.spinner3);

        String[] genders = new String[]{"-Please choose gender-","Female", "Male", "Other", "Prefer not to say"};
        String[] smoking = new String[]{"-Select nicotine consumption-","I don't use nicotine", "I occasionally use nicotine", "I use nicotine almost daily", "I use nicotine daily"};
        String[] diet = new String[]{"-Please choose your diet-","Vegan","Vegetarian","Mostly plant based diet","Regular diet"};

        ArrayAdapter<String> adapterGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, genders);
        ArrayAdapter<String> adapterSmoking = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, smoking);
        ArrayAdapter<String> adapterDiet = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, diet);

        spinnerGender.setAdapter(adapterGender);
        spinnerSmoking.setAdapter(adapterSmoking);
        spinnerDiet.setAdapter(adapterDiet);

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                password = encryptPassword(passwordInput.getText().toString());
                String checkPassword = passwordInput.getText().toString();
                passwordRepeat = passwordRepeatInput.getText().toString();
                if(checkPassword.equals(passwordRepeat)==false){
                    showToast("Passwords do not match!");
                }
                else if(checkPassword.equals(passwordRepeat)) {
                    name = nameInput.getText().toString();
                    birthday = birthdayInput.getText().toString();
                    height = Integer.parseInt(heightInput.getText().toString());
                    weight = Integer.parseInt(weightInput.getText().toString());
                    gender = spinnerGender.getSelectedItem().toString();
                    dietaryInfo = spinnerDiet.getSelectedItem().toString();
                    smokingInfo = spinnerSmoking.getSelectedItem().toString();


                    User newUser = new User(name, password);
                    newUser.setBirthday(birthday);
                    newUser.setHeight(height);
                    newUser.setWeight(weight);
                    newUser.setGender(gender);
                    newUser.setDietaryInfo(dietaryInfo);
                    newUser.setSmokingInfo(smokingInfo);
                    SecondActivity();
                }
            }
        });
    }

    private void SecondActivity(){
        Intent intent = new Intent(MainActivity3.this, MainActivity2.class);
        this.startActivity(intent);
    }

    private void showToast(String text){
        Toast.makeText(MainActivity3.this, text, Toast.LENGTH_SHORT).show();
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