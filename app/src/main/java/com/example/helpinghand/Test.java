package com.example.helpinghand;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.webkit.HttpAuthHandler;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.helpinghand.database.Database;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Test extends AppCompatActivity {

    ArrayList<String> postcode = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);

        URL url;
        String newLink;
        String line;

        Database user = new Database();
        
            try {
                postcode = user.checkPostcode();
                for(String singlePostcode:postcode) {
                    String response ="";

                    newLink = "https://maps.googleapis.com/maps/api/geocode/json?address=" + singlePostcode + "," +
                            "&key=AIzaSyCdt-iQdK_cdnqplCTpbe48ajOuVgVqDhw\n";
                    url = new URL(newLink);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        response += line;

                    }
                    JSONObject jo = new JSONObject(response);
                    String lat = ((JSONArray) jo.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat").toString();
                    String lng =  ((JSONArray) jo.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lng").toString();
                    System.out.println("lat" + lat);
                    ((TextView) findViewById(R.id.testView)).setText(lng+", "+lat);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }




   /*

        URL url;
        String response ="";
        String newLink;
        String postcode = "WC2N 5DU";
        String line;

        newLink = "https://maps.googleapis.com/maps/api/geocode/json?address="+postcode+"," +
                "&key=AIzaSyCdt-iQdK_cdnqplCTpbe48ajOuVgVqDhw\n";

        try {
            url = new URL(newLink);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            while((line = br.readLine()) != null){
                response += line;
                System.out.println("response"+response);
            }
            JSONObject jo = new JSONObject(response);
            String lat = ((JSONArray) jo.get("results")).getJSONObject(0).getJSONObject("geometry").getJSONObject("location").get("lat").toString();
            System.out.println("lat" + lat);
            TextView tv = (TextView) findViewById(R.id.testView);
            tv.setText(lat);
            ((TextView) findViewById(R.id.testView)).setText(lat);


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        "https://maps.googleapis.com/maps/api/geocode/json?address=" + singlePostcode + "," +
                            "&key=AIzaSyCdt-iQdK_cdnqplCTpbe48ajOuVgVqDhw\n"
        */

    }
}