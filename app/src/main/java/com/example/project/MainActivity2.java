package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity2 extends AppCompatActivity {

    public MainActivity4 instance;

    TextView Text_data;

    int beefAmount = 0;
    int fishAmount = 0;
    int porkAmount = 0;
    int dairyAmount = 0;
    int cheeseAmount = 0;
    int riceAmount = 0;
    int eggAmount = 0;
    int saladAmount = 0;
    int restaurantAmount = 0;
    Boolean carbonPreference = false;
    String dietChoice = "omnivore";


    public void setData1(){
        String[] data = MainActivity4.data;

        if (data[1].equals("Yes")){
            carbonPreference = true;
        }
        else{
            carbonPreference = false;
        }

        if (data[0].equals("Vegetarian") ){
            dietChoice = "vegetarian";
        }
        else if (data[0].equals("Vegan")) {
            dietChoice = "vegan";
        }
        else{
            dietChoice = "omnivore";
        }

        beefAmount = Integer.parseInt(data[2]);
        fishAmount = Integer.parseInt(data[3]);
        porkAmount = Integer.parseInt(data[4]);
        dairyAmount = Integer.parseInt(data[5]);
        cheeseAmount = Integer.parseInt(data[6]);
        riceAmount = Integer.parseInt(data[7]);
        eggAmount = Integer.parseInt(data[8]);
        restaurantAmount = Integer.parseInt(data[9]);


    }

    String url = "https://ilmastodieetti.ymparisto.fi/ilmastodieetti/calculatorapi/v1/FoodCalculator?query.diet="+dietChoice+"&query.lowCarbonPreference="+carbonPreference+"&query.beefLevel="+beefAmount+"&query.fishLevel="+fishAmount+"&query.porkPoultryLevel="+porkAmount+"&query.dairyLevel="+dairyAmount+"&query.cheeseLevel="+cheeseAmount+"&query.riceLevel="+riceAmount+"&query.eggLevel="+eggAmount+"&query.winterSaladLevel="+saladAmount+"&query.restaurantSpending="+restaurantAmount+"&api_key=10";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ImageButton logoutButton;
        logoutButton = (ImageButton) findViewById(R.id.imageButton_logout);

        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirstActivity();
            }
        });

        Text_data = (TextView) findViewById((R.id.DataTextview));

        Button showGraphButton;
        showGraphButton = (Button) findViewById(R.id.btn_ShowGraph);

        showGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Instantiate everything.

                    setData1();
                    getResponse();
                //Text_data.setText(url);


            }
        });

        Button editDataButton;
        editDataButton = (Button) findViewById(R.id.btn_EditData);

        editDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FourthActivity();
            }
        });

    }
    private void FirstActivity(){
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        this.startActivity(intent);
    }

    private void FourthActivity(){
        Intent intent = new Intent(MainActivity2.this, MainActivity4.class);
        this.startActivity(intent);
    }


    private void getResponse() {


        // Request a string response from the provided URL.
        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {

                    double dairyAmount = response.getDouble("Dairy");
                    double meatAmount = response.getDouble("Meat");
                    double plantAmount = response.getDouble("Plant");
                    double restaurantAmount = response.getDouble("Restaurant");
                    double totalAmount = response.getDouble("Total");

                    //Toast.makeText(MainActivity2.this, Double.toString(dairyAmount), Toast.LENGTH_SHORT).show();
                    Text_data.setText("Dairy: "+Double.toString(dairyAmount)+"\nMeat: "+Double.toString(meatAmount)+
                            "\nPlant: "+Double.toString(plantAmount)+"\nRestaurant: "+Double.toString(restaurantAmount)+
                            "\nTotal:"+Double.toString(totalAmount));


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Text_data.setText("That didn't work!");
            }

        });
        queue.add(request);
    }


}