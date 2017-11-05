package com.example.kaves.nfc;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class MainActivity extends Activity {
    Button SubmitButton;
    EditText EmailEdit;
    EditText PasswordEdit;
    TextView ResponseReply;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SubmitButton = (Button) findViewById(R.id.LoginButton);
        EmailEdit = (EditText) findViewById(R.id.EmailField);
        PasswordEdit = (EditText) findViewById(R.id.PasswordField);
        ResponseReply = (TextView) findViewById(R.id.ResponseDisplay);

        final RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.start();

        SubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://dcb-hackathon-cryogenicplanet.c9users.io/login";
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("email", EmailEdit.getText().toString());
                params.put("password", PasswordEdit.getText().toString());
                JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                ResponseReply.setText("Success!");
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        ResponseReply.setText("Error" + error.toString());
                    }
                });
                queue.add(request);
                }
            });
        }
    }